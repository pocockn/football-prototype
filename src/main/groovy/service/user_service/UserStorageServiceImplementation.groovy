package service.user_service

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.inject.Inject
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import groovy.util.logging.Slf4j
import persistance.JsonObjectMapper
import ratpack.exec.Blocking
import ratpack.exec.Operation
import ratpack.exec.Promise
import user.UserProfile

@Slf4j
class UserStorageServiceImplementation implements UserStorageService<UserProfile> {

    @Inject
    Sql sql
    @Inject
    ObjectMapper mapper
    @Inject
    JsonObjectMapper jsonObjectMapper

    @Override
    Promise<List<?>> fetchAll() {
        Blocking.get {
            sql.rows("select * from users").collect { GroovyRowResult result ->
                String instanceJson = result.getAt(1)
                UserProfile instance = mapper.readValue(instanceJson, UserProfile)
                return instance
            }
        }
    }

    @Override
    Promise<?> fetch(String id) {
        if (id == null) {
            return null
        }
        Blocking.get {
            String query = "SELECT * FROM users WHERE id = '${id}'"
            (String) sql.firstRow(query)?.getAt(1)
        }.map { json ->
            UserProfile instance = json ? mapper.readValue(json, UserProfile) : null
            instance
        }
    }

    @Override
    Operation delete(String id) {
        if (id == null) {
            return null
        }
        Blocking.get {
            sql.execute "DELETE FROM users WHERE id = ${id}"
        }.operation()
    }


    @Override
    Promise<?> saveUser(UserProfile userProfile) {
        try {
            String json = jsonObjectMapper.mapObjectToJson(userProfile)
            Blocking.get {
                sql.execute("INSERT INTO users (id, content) VALUES (?, cast(? as json))", userProfile.openId, json)
                return userProfile
            }
        } catch (e) {
            throw e
        }
    }
}

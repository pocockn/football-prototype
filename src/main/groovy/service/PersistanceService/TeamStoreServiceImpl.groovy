package service.PersistanceService

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import groovy.util.logging.Slf4j
import models.Team
import models.TeamContainer
import persistance.JsonObjectMapper
import ratpack.exec.Blocking
import ratpack.exec.Operation
import ratpack.exec.Promise

import javax.inject.Inject

@Slf4j
class TeamStoreServiceImpl implements StoreService<TeamContainer> {

    @Inject
    Sql sql
    @Inject
    ObjectMapper objectMapper
    @Inject
    JsonObjectMapper jsonObjectMapper

    @Override
    Operation save(TeamContainer team) {
        int updates = 0;
        try {
            String json = jsonObjectMapper.mapObjectToJson(team)
            Blocking.get {
                updates = sql.executeUpdate("update site_content set content = cast(? as jsonb), where id = ?", json, team.id)
            }
            if (updates == 0) {
                Blocking.get {
                    sql.execute("INSERT INTO site_content (id, content) VALUES (?, cast(? as jsonb))", team.id, json)
                }.operation()
            }
        } catch (e) {
            throw e
        }
    }

    @Override
    Operation delete(String id) {
        Blocking.get {
            sql.execute("DELETE FROM site_content where id = ${id}")
        }.operation()
    }

    @Override
    Promise<List<Team>> fetchAll() {
        Blocking.get {
            sql.rows("""
            SELECT * from site_content
              """)
        }.map { rows ->
            rows.collect { GroovyRowResult result ->
                String instanceJson = result.getAt(1)
                objectMapper.readValue(instanceJson, Team)
            }
        }
    }

    @Override
    Promise<TeamContainer> fetchById(String id) {
        if (id == null) {
            return null
        }
        log.info("id ${id}")
        Blocking.get {
            sql.firstRow("""SELECT * from site_content where id = ${id}""")
        }.map { row ->
            if (row) {
                objectMapper.readValue(row.getAt(1).toString(), TeamContainer)
            }
        }
    }
}

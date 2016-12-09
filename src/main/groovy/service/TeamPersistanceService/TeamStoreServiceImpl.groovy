package service.TeamPersistanceService

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import models.Team
import persistance.JsonObjectMapper
import ratpack.exec.Blocking
import ratpack.exec.Operation
import ratpack.exec.Promise

import javax.inject.Inject

class TeamStoreServiceImpl implements TeamStoreService {

    @Inject
    Sql sql
    @Inject
    ObjectMapper objectMapper
    @Inject
    JsonObjectMapper jsonObjectMapper

    @Override
    Operation save(Team team) {
        String json = jsonObjectMapper.mapObjectToJson(team)
        Blocking.get {
            sql.execute("INSERT INTO site_content (id, content) VALUES (?, cast(? as jsonb))", team.id, json)
        }.operation()
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
    Promise<Team> fetchById(String id) {
        if (id == null) {
            return null
        }

        Blocking.get {
            sql.firstRow("""SELECT * from site_content where id = ${id}""")
        }.map { row ->
            if (row) {
                objectMapper.readValue(row.getAt(0).toString(), Team)
            }
        }
    }
}

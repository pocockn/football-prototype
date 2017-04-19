package service.persistance_service

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import groovy.util.logging.Slf4j
import models.Fixtures
import models.Player
import models.TeamContainer
import persistance.JsonObjectMapper
import ratpack.exec.Blocking
import ratpack.exec.Operation
import ratpack.exec.Promise

import javax.inject.Inject

@Slf4j
class TeamStoreServiceImpl implements TeamStoreService<TeamContainer> {

    @Inject
    Sql sql
    @Inject
    ObjectMapper objectMapper
    @Inject
    JsonObjectMapper jsonObjectMapper

    @Override
    Operation save(TeamContainer team) {
        int updates = 0
        try {
            String json = jsonObjectMapper.mapObjectToJson(team)
            Blocking.get {
                updates = sql.executeUpdate("update site_content set content = cast(? as jsonb), where id = ?", json, team.id)
            }
            if (updates == 0) {
                Blocking.get {
                    sql.execute("INSERT INTO site_content (id, content) VALUES (?, cast(? as jsonb))", team.id, json)
                    log.info("team added with ID of ${team.id}")
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
    Promise<List<TeamContainer>> fetchAll() {
        Blocking.get {
            sql.rows("""
            SELECT * from site_content
              """)
        }.map { rows ->
            rows.collect { GroovyRowResult result ->
                String instanceJson = result.getAt(1)
                objectMapper.readValue(instanceJson, TeamContainer)
            }
        }
    }

    @Override
    Promise<TeamContainer> fetchById(String id) {
        if (id == null) {
            return null
        }
        Blocking.get {
            sql.firstRow("""SELECT * from site_content where id = ?""", id)
        }.map { row ->
            if (row) {
                objectMapper.readValue(row.getAt(1).toString(), TeamContainer)
            }
        }
    }

    @Override
    Promise<List<Player>> fetchPlayerIds(String id) {
        Blocking.get {
            sql.rows("""
            SELECT jsonb_array_elements(content->'playersContainer'->'players') as players FROM site_content
            WHERE id = ${id}
              """)
        }.map { row ->
            if (row) {
                row.players.collect {
                    def playerObject = objectMapper.readValue(it.toString(), Player)
                    playerObject
                }

            }
        }
    }

    @Override
    Promise<Fixtures> fetchFixtures(String id) {
        Blocking.get {
            sql.firstRow("select content->'fixtures' from site_content where id = ${id}")
        }.map { row ->
            if (row) {
                objectMapper.readValue(row.getAt(0).toString(), Fixtures)
            }
        }
    }
}


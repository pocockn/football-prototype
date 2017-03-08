package service.persistance_service

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import groovy.util.logging.Slf4j
import models.Player
import persistance.JsonObjectMapper
import ratpack.exec.Blocking
import ratpack.exec.Operation
import ratpack.exec.Promise

import javax.inject.Inject

@Slf4j
class PlayerStoreServiceImpl implements PlayerStoreService<Player> {

    @Inject
    Sql sql
    @Inject
    ObjectMapper objectMapper
    @Inject
    JsonObjectMapper jsonObjectMapper

    @Override
    Operation save(Player player) {
        List<GroovyRowResult> updates = []
        try {
            String json = jsonObjectMapper.mapObjectToJson(player)
            Blocking.get {
                updates = sql.rows("select * from players where id = ?", player.id)
                log.info("$updates")
                if (updates.size() > 0) {
                    sql.execute("update players set player_content = cast(? as jsonb) where id = ?", json, player.id)

                } else {
                    sql.execute("INSERT INTO players (id, player_content) VALUES (?, cast(? as jsonb))", player.id, json)
                }
            }.operation()
        } catch (e) {
            throw e
        }
    }

    @Override
    Operation delete(String id) {
        Blocking.get {
            sql.execute("DELETE FROM players WHERE id = ?", id)
        }.operation()
    }

    @Override
    Promise<List<Player>> fetchAll() {
        Blocking.get {
            sql.rows("""
            SELECT * from players
              """)
        }.map { rows ->
            rows.collect { GroovyRowResult result ->
                String instanceJson = result.getAt(1)
                objectMapper.readValue(instanceJson, Player)
            }
        }
    }

    @Override
    Promise<Player> fetchById(String id) {
        if (id == null) {
            return null
        }
        Blocking.get {
            sql.firstRow("SELECT * FROM players WHERE id = ?", id)
        }.map { row ->
            if (row) {
                String instanceJson = row.getAt(1)
                objectMapper.readValue(instanceJson, Player)
            }
        }
    }
}

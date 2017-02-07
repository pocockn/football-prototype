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
        String json = objectMapper.writeValueAsString(player)
        Blocking.get {
            sql.executeUpdate("""
                UPDATE site_content
                SET content = jsonb_set(content, '{playersContainer,players}'::text[], content->'playersContainer'->'players' || '${
                json
            }'::jsonb)
                where id = :id
                """, id: player.teamId)
        }.operation()
    }

    @Override
    Operation delete(String id) {
        Blocking.get {
            sql.execute("DELETE FROM site_content where id = ${id}")
        }.operation()
    }

    @Override
    Promise<List<Player>> fetchAll() {
        Blocking.get {
            sql.rows("""
            SELECT * from site_content
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
            sql.firstRow("""select elem
                                from site_content,
                                lateral jsonb_array_elements(content->'playersContainer'->'players') elem
                                where elem @> '{"id": "${id}"}'
                             """)
        }.map { row ->
            log.info("row is: ${row.getAt(0)}")
            if (row) {
                String instanceJson = row.getAt(0)
                objectMapper.readValue(instanceJson, Player)
            }
        }
    }
}

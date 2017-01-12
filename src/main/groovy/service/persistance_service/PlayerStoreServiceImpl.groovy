package service.persistance_service

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import models.Player
import persistance.JsonObjectMapper
import ratpack.exec.Blocking
import ratpack.exec.Operation
import ratpack.exec.Promise

import javax.inject.Inject

class PlayerStoreServiceImpl implements StoreService<Player> {

    @Inject
    Sql sql
    @Inject
    ObjectMapper objectMapper
    @Inject
    JsonObjectMapper jsonObjectMapper

    @Override
    Operation save(Player player) {
        String json = jsonObjectMapper.mapObjectToJson(player)
        Blocking.get {
            sql.execute("INSERT INTO site_content (id, content) VALUES (?, cast(? as jsonb))", player.id, json)
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
            sql.firstRow("""SELECT * from site_content where id = ${id}""")
        }.map { row ->
            if (row) {
                objectMapper.readValue(row.getAt(0).toString(), Player)
            }
        }
    }
}

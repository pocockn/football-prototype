package persistance

import groovy.sql.Sql

class PersistanceSpec extends DatabaseCleaner {

    void "Can insert a new record into the database"() {
        given:
        String json = """{"series":[{"name":"Nick","data":[0,2,3,5,6]},{"name":"Pasty","data":[0,5,7,9,10]}]}"""
        String id = 2

        when:
        remoteControl.exec {
            get(Sql).execute("INSERT INTO test (id, content) VALUES (?, cast(? as json))", id, json)
        }

        and:
        def result = sql.rows("SELECT * FROM test where id = '2'")

        then:
        String jsonResult = result.toString()
        jsonResult.contains(json)

        cleanup:
        cleanDatabase()

    }
}

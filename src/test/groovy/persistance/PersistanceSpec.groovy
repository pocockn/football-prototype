package persistance

class PersistanceSpec extends BaseDatabaseTestConnection {

    void "Can insert a new record into the database"() {
        given:
        DatabaseCleaner databaseCleaner = new DatabaseCleaner()
        String json = """{"series":[{"name":"Nick","data":[0,2,3,5,6]},{"name":"Pasty","data":[0,5,7,9,10]}]}"""
        String id = 2

        when:
        sql.execute("INSERT INTO site_content (id, content) VALUES (?, cast(? as json))", id, json)
        def result = sql.rows("SELECT * FROM site_content where id = '2'")

        then:
        String jsonResult = result.toString()
        jsonResult.contains(json)

        cleanup:
        databaseCleaner.cleanDatabase(sql)

    }
}

package persistance

class PersistanceSpec extends BaseDatabaseTestConnection {

    void "Can insert a new record into the database"() {
        given:
        DatabaseCleaner databaseCleaner = new DatabaseCleaner()
        String json = """{"series":[{"name":"Nick","data":[0,2,3,5,6]},{"name":"Pasty","data":[0,5,7,9,10]}]}"""

        when:
        sql.executeInsert("INSERT into site_content (id, content) VALUES (2, ?)", json)
        def result = sql.rows("SELECT * FROM site_content where id = 2")

        then:
        result.contains("Nick")

        cleanup:
        databaseCleaner.cleanDatabase()

    }
}

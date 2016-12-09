package persistance

import groovy.sql.Sql

class DatabaseCleaner extends BaseDatabaseTestConnection{

    void cleanDatabase() {
        try {
            sql.executeUpdate("truncate site_content")
        } finally {
            sql.close()
        }
    }

}

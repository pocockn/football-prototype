package persistance

import groovy.sql.Sql

class DatabaseCleaner {

    static void cleanDatabase(Sql sql) {
        try {
            sql = new Sql(sql.getDataSource().connection)
            sql.executeUpdate("truncate site_content")
        } finally {
            sql.close()
        }
    }

}

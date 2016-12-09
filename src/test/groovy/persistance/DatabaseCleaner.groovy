package persistance

import groovy.sql.Sql
import groovy.util.logging.Slf4j

@Slf4j
class DatabaseCleaner extends BaseDatabaseTestConnection{

    void cleanDatabase(Sql sql) {
        try {
            sql.execute("truncate site_content")
        } catch(Exception e) {
            log.error("error is ${e}")
        }
    }

}

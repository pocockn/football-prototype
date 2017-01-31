package persistance

import groovy.sql.Sql
import groovy.util.logging.Slf4j

@Slf4j
class DatabaseCleaner extends BaseDatabaseTestConnection {

    void cleanDatabase() {
        try {
            remoteControl.exec {
                get(Sql).execute("delete from test")
                log.info("deleted data from test database")
            }
        } catch (Exception e) {
            log.error("error is ${e}")
        }
    }

}

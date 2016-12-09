package persistance

import com.google.inject.Inject
import config.DatabaseConfig
import groovy.util.logging.Slf4j
import liquibase.LabelExpression
import liquibase.Liquibase
import liquibase.database.Database
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import ratpack.service.Service
import ratpack.service.StartEvent

import javax.sql.DataSource
import java.sql.Connection

@Slf4j
class DataMigrationService implements Service {

    public static final String CHANGELOG_RESOURCE_URI = "db-master-changelog.xml"

    @Inject
    DataSource dataSource

    @Inject
    DatabaseConfig databaseConfig

    @Override
    void onStart(StartEvent event) throws Exception {
        migrate()
    }

    public void migrate() {
        Connection connection = dataSource.getConnection()
        log.info("Connecting using: ${connection}")
        try {
            log.info('Migrating data across')
            migrateWithConnection(connection)
        } catch (Exception e) {
            log.error("Error migrating data", e)
        } finally {
            connection?.close()
        }
    }

    private void migrateWithConnection(Connection connection) {
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection))

        Liquibase liquibase = new Liquibase(CHANGELOG_RESOURCE_URI, new ClassLoaderResourceAccessor(getClass().getClassLoader()), database)
        liquibase.update(databaseConfig.liquibaseContexts, new LabelExpression())
    }
}

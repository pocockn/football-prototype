package ratpack_modules

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import com.zaxxer.hikari.HikariConfig
import config.DatabaseConfig

class HikariConfigModule extends AbstractModule {

    static final int MAX_POOL_SIZE_GIVEN_HOBBY_DB_AND_ONE_WEB_DYNO = 4

    public static final String HEROKU_DB_URI_ENV = "DATABASE_URL"

    public static final String SNAP_ENV = 'SNAP_CI'
    public static final String SNAP_DB_HOST_ENV = 'SNAP_DB_PG_HOST'
    public static final String SNAP_DB_PORT_ENV = 'SNAP_DB_PG_PORT'
    public static final String SNAP_DB_USER_ENV = 'SNAP_DB_PG_USER'
    public static final String SNAP_DB_PASSWORD_ENV = 'SNAP_DB_PG_PASSWORD'

    protected void configure() {
    }

    @Provides
    @Singleton
    HikariConfig provideHikariConfig(DatabaseConfig config) {
        HikariConfig hc = new HikariConfig(jdbcUrl: config.jdbcUrl, username: config.dbUser, password: config.dbPassword,
                maximumPoolSize: MAX_POOL_SIZE_GIVEN_HOBBY_DB_AND_ONE_WEB_DYNO, connectionTestQuery: "SELECT * FROM site_content")

        Map<String, String> environment = System.getenv()
        if (environment[HEROKU_DB_URI_ENV]) {
            URI dbUri = new URI(environment[HEROKU_DB_URI_ENV])
            def userDetails = dbUri.getUserInfo().split(":")
            hc.username = userDetails[0]
            hc.password = userDetails[1]
            hc.jdbcUrl = "jdbc:postgresql://${dbUri.host}:${dbUri.port}${dbUri.path}"
        } else if (environment[SNAP_ENV]) {
            config.dbHost = environment[SNAP_DB_HOST_ENV]
            config.port = environment[SNAP_DB_PORT_ENV].toInteger()
            hc.username = environment[SNAP_DB_USER_ENV]
            hc.password = environment[SNAP_DB_PASSWORD_ENV]
            hc.jdbcUrl = config.jdbcUrl
        }
        return hc
    }
}
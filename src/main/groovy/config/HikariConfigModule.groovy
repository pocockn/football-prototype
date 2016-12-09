package config

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import com.zaxxer.hikari.HikariConfig

/**
 * Created by pocockn on 07/06/16.
 */
class HikariConfigModule extends AbstractModule {

    static final int MAX_POOL_SIZE_GIVEN_HOBBY_DB_AND_ONE_WEB_DYNO = 4

    protected void configure() {
    }

    @Provides
    @Singleton
    HikariConfig provideHikariConfig(DatabaseConfig config) {
        HikariConfig hc = new HikariConfig(jdbcUrl: config.jdbcUrl, username: config.dbUser, password: config.dbPassword,
                maximumPoolSize: MAX_POOL_SIZE_GIVEN_HOBBY_DB_AND_ONE_WEB_DYNO, connectionTestQuery: "SELECT * FROM site_content")
        return hc
    }
}
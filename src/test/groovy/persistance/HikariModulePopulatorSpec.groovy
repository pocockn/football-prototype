package persistance

import config.DatabaseConfig
import config.HikariConfigModule
import spock.lang.Shared
import spock.lang.Specification

class HikariModulePopulatorSpec extends Specification {

    @Shared
    HikariConfigModule hikariConfigModule = new HikariConfigModule()

    void "test that heroku populates the hikari config module"() {
        given:
        GroovyMock(System, global: true)
        System.getenv() >> [
                'DATABASE_URL': 'postgres://user:pass@somehost.compute.amazonaws.com:5432/database-name',
        ]
        when:
        def config = hikariConfigModule.provideHikariConfig(new DatabaseConfig())

        then:
        config.username == "user"
        config.password == "pass"
        config.jdbcUrl == "jdbc:postgresql://somehost.compute.amazonaws.com:5432/database-name"
    }

    void "test that snap ci populates the hikari config module with correct credentials"() {
        given:
        GroovyMock(System, global: true)
        System.getenv() >> [
                'DATABASE_URL': 'postgres://snap:ci@snapci.com/databasename'
        ]

        when:
        def config = hikariConfigModule.provideHikariConfig(new DatabaseConfig())

        then:
        config.username == 'snap'
        config.password == 'ci'
        config.jdbcUrl == "jdbc:postgresql://snapci.com:-1/databasename"
    }
}

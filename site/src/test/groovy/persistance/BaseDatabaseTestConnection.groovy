package persistance

import Fixture.FootballApplicationUnderTest
import groovy.sql.Sql
import groovy.util.logging.Slf4j
import io.remotecontrol.client.UnserializableResultStrategy
import ratpack.test.ApplicationUnderTest
import ratpack.test.remote.RemoteControl
import spock.lang.Shared
import spock.lang.Specification

@Slf4j
class BaseDatabaseTestConnection extends Specification {

    @Shared
    ApplicationUnderTest aut = new FootballApplicationUnderTest()
    @Shared
    RemoteControl remoteControl = new RemoteControl(aut, UnserializableResultStrategy.NULL)

    String dbHost = "127.0.0.1"
    String dbUser = "pocockn"
    String db = "footballprototype"
    String dbPassword = "password"
    Integer port = 5432

    String jdbcUrl = "jdbc:postgresql://${getDbHost()}:${getPort()}/${getDb()}"

    Sql sql = Sql.newInstance(jdbcUrl, dbUser, dbPassword)

}

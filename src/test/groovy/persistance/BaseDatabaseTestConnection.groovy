package persistance

import groovy.sql.Sql
import spock.lang.Specification

class BaseDatabaseTestConnection extends Specification{

    String dbHost = "127.0.0.1"
    String dbUser = "pocockn"
    String db = "footballprototype"
    String dbPassword = "only8deb"
    Integer port = 5432

    String jdbcUrl = "jdbc:postgresql://${getDbHost()}:${getPort()}/${getDb()}"

    Sql sql = Sql.newInstance(jdbcUrl, dbUser, dbPassword)

}

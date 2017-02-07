package config

import liquibase.Contexts

class DatabaseConfig {

    String dbHost = "127.0.0.1"
    String dbUser = "pocockn"
    String db = "footballprototype"
    String dbPassword = "password"
    Integer port = 5432
    Contexts liquibaseContexts = new Contexts()

    String getJdbcUrl() {
        "jdbc:postgresql://${getDbHost()}:${getPort()}/${getDb()}"
    }

}
package changesets

import groovy.sql.Sql
import liquibase.change.custom.CustomTaskChange
import liquibase.database.Database
import liquibase.database.jvm.JdbcConnection
import liquibase.exception.CustomChangeException
import liquibase.exception.SetupException
import liquibase.exception.ValidationErrors
import liquibase.resource.ResourceAccessor

abstract  class CustomChangeSupport implements CustomTaskChange {

    ResourceAccessor fileOpener
    Sql sql

    @Override
    String getConfirmationMessage() {
        "Migration complete"
    }

    void setUp() throws SetupException {
    }

    @Override
    ValidationErrors validate(Database database) {
    }

    @Override
    void execute(Database database) throws CustomChangeException{
        JdbcConnection connection = database.connection
        sql = new Sql(connection.underlyingConnection)
        execute(sql)
    }

    abstract void execute(Sql sql)
}

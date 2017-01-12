package changesets

import groovy.sql.Sql
import liquibase.change.custom.CustomTaskChange
import liquibase.database.Database
import liquibase.database.jvm.JdbcConnection
import liquibase.exception.SetupException
import liquibase.exception.ValidationErrors
import liquibase.resource.ResourceAccessor

class BootstrapShireSoldiersChange implements CustomTaskChange {

    ResourceAccessor fileOpener
    Sql sql

    static final String ID = "0000-0000-0000-0001"

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
    void execute(Database database) {
        JdbcConnection connection = database.connection
        sql = new Sql(connection.underlyingConnection)
        sql.execute("insert into site_content (id, content) values (?, cast(? as json))", ID, CONTENT.toString())
    }

    static final String CONTENT = new StringBuffer("""
    {
    "id": "${ID}",
    "team": {
        "id": "123",
        "name": "Shire Soldiers"
    },
    "playersContainer": {
        "series": [
            {
                "id": "1",
                "name": "Nick",
                "teamName": "Shire Soldiers",
                "ratings": [
                    1,
                    5,
                    6,
                    9
                ],
                "assists": 17,
                "manOfTheMatches": 20,
                "cleanSheets": 1,
                "data": [
                    3,
                    2,
                    3,
                    5,
                    6
                ],
                "totalGoals": 19
            },
            {
                "id": "2",
                "name": "Pasty",
                "teamName": "Shire Soldiers",
                "ratings": [
                    6,
                    8,
                    9,
                    10
                ],
                "assists": 25,
                "manOfTheMatches": 32,
                "cleanSheets": 2,
                "data": [
                    3,
                    5,
                    7,
                    9,
                    10
                ],
                "totalGoals": 24
            }
        ]
    },
    "fixtures": {
        "matches": [
            {
                "id": "1234",
                "title": "match two",
                "start": "2017-01-01T17:40:16.264Z",
                "end": "2017-01-01T17:40:16.264Z",
                "className": "fc-event-primary",
                "textColor": "black"
            },
            {
                "id": "123",
                "title": "match one",
                "start": "2017-01-01T17:40:16.215Z",
                "end": "2017-01-01T17:40:16.258Z",
                "className": "fc-event-primary",
                "textColor": "black"
            }
        ]
    }
}
    """).toString()

}

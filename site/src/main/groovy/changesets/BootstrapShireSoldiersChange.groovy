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
        sql.execute("insert into site_content (id, content) values (?, cast(? as jsonb))", ID, CONTENT.toString())
    }

    static final String CONTENT = new StringBuffer("""
    {
    "id": "${ID}",
    "team": {
        "id": "123",
        "name": "Shire Soldiers"
    },
    "playersContainer": {
        "players": [
            {
                "id": "1",
                "name": "Nick Pocock",
                "teamName": "Shire Soldiers",
                "bio" : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla imperdiet lorem tellus, in bibendum sem dignissim sed. Etiam eu elit sit amet lacus accumsan blandit sed ut dolor. Mauris vel dui non nisi vestibulum commodo vel id magna. Donec egestas magna in tincidunt mollis. Fusce mauris arcu, rhoncus ut lacus sed, fermentum ultrices elit. In sollicitudin at ex dapibus vestibulum. Pellentesque congue, est id lobortis viverra, mauris lectus pharetra orci, ut suscipit nisl purus vehicula est. Aliquam suscipit non velit vel feugiat. Quisque nec dictum augue.",
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
                "name": "Connor Pasty",
                "teamName": "Shire Soldiers",
                "bio" : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla imperdiet lorem tellus, in bibendum sem dignissim sed. Etiam eu elit sit amet lacus accumsan blandit sed ut dolor. Mauris vel dui non nisi vestibulum commodo vel id magna. Donec egestas magna in tincidunt mollis. Fusce mauris arcu, rhoncus ut lacus sed, fermentum ultrices elit. In sollicitudin at ex dapibus vestibulum. Pellentesque congue, est id lobortis viverra, mauris lectus pharetra orci, ut suscipit nisl purus vehicula est. Aliquam suscipit non velit vel feugiat. Quisque nec dictum augue.",
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

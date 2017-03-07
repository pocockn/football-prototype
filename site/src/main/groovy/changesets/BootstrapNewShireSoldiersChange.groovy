package changesets

import groovy.sql.Sql

class BootstrapNewShireSoldiersChange extends CustomChangeSupport {

    static final String ID = "0000-0000-0000-0001"


    @Override
    void execute(Sql sql) {
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
                "id": "0000-0000-0000-0001"
            },
            {
                "id": "0000-0000-0000-0002"
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

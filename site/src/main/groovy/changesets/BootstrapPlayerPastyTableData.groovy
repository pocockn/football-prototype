package changesets

import groovy.sql.Sql

class BootstrapPlayerPastyTableData extends CustomChangeSupport {

    static final String ID = "0000-0000-0000-0007"


    @Override
    void execute(Sql sql) {
        sql.execute("insert into players (id, player_content) values (?, cast(? as jsonb))", ID, CONTENT.toString())
    }

    static final String CONTENT = new StringBuffer("""
    {
        "id": "${ID}",
        "name": "Connor Pasty",
        "teamName": "Shire Soldiers",
        "teamId": "0000-0000-0000-0007",
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
    """).toString()
}
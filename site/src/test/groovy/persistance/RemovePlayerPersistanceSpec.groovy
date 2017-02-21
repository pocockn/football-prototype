package persistance

import groovy.sql.Sql
import groovy.util.logging.Slf4j
import spock.lang.Ignore

@Slf4j
@Ignore
class RemovePlayerPersistanceSpec extends DatabaseCleaner {

    void "Can delete a player from the database"() {
        given:
        String json = """{
        "id": "1",
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
        }
        }"""

        when:
        remoteControl.exec {
            get(Sql).execute("INSERT INTO test (id, content) VALUES (?, cast(? as json))", '2', json)
        }

        and:
        def result = sql.execute("""
                update test c
                set content = 
                    jsonb_set(
                        content, 
                        '{playersContainer,players}',
                        (
                            select jsonb_agg(elem)
                            from site_content cc,
                            lateral jsonb_array_elements(content->'playersContainer'->'players') elem
                            where c.id = cc.id
                            and not (elem @> '{"id": "1"}')
                        )
                    )
           """)

        then:
        String jsonResult = result.toString()
        log.info("${jsonResult}")

        cleanup:
        cleanDatabase()

        where:
        id = 2


    }
}

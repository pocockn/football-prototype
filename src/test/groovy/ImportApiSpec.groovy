import groovy.json.JsonSlurper
import models.LeagueTable
import spock.lang.Specification

/**
 * Created by pocockn on 26/01/17.
 */
class ImportApiSpec extends Specification {
    void "Test to see if we can get our JSON to format well or map to object"() {
        given:
        JsonSlurper jsonSlurper = new JsonSlurper()
        File f = new File('/home/pocockn/dev/football/src/test/resources/importJsonData.json')
        def jsonSlurped = jsonSlurper.parseText(f.text)
        LeagueTable leagueTable = new LeagueTable()

        when:
        jsonSlurped.result.extractorData.data.group.Content.text.each { firstNested ->
            firstNested.each { secondNested ->
                secondNested.each { finalValue ->
                    leagueTable.teams.add(finalValue)
                }
            }
        }

        then:
        leagueTable.teams.contains("Shire Soldiers")

    }
}

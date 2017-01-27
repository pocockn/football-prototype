import groovy.json.JsonSlurper
import models.LeagueTable
import spock.lang.Specification

class ImportApiSpec extends Specification {
    void "Parse JSON league data, ensure it has correct info in"() {
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
        leagueTable.teams.contains("Wavey Wanderers")
        leagueTable.teams.contains("Clapham Junction Thursday")

    }
}

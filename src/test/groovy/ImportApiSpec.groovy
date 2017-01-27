import groovy.json.JsonSlurper
import models.LeagueTable
import spock.lang.Specification

class ImportApiSpec extends Specification {
    void "Parse JSON league data, ensure it has correct info in"() {
        given:
        ClassLoader classLoader = getClass().getClassLoader()
        File jsonFile = new File(classLoader.getResource("importJsonData.json").getFile())
        JsonSlurper jsonSlurper = new JsonSlurper()
        def jsonSlurped = jsonSlurper.parseText(jsonFile.text)
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

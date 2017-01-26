package service

import groovy.json.JsonSlurper
import models.LeagueTable

class LeagueTableHelpers {

    JsonSlurper jsonSlurper = new JsonSlurper()
    File f = new File('/home/pocockn/dev/football/src/test/resources/importJsonData.json')
    def jsonSlurped = jsonSlurper.parseText(f.text)
    ArrayList<String> objectProperties = ["teams", "played", "won", "draw", "lose", "goalDifference", "goalsFor", "goalsAgainst", "points"]
    List<String> jsonSelectors = ["Content", "Value 1", "Value 2", "Value 3", "Value 4", "Value 5", "Value 6", "Value 7", "Value 8"]
    LeagueTable leagueTable = new LeagueTable()


    public List<String> storeItemsInList() {
        int i = 0
        jsonSelectors.each {
            jsonSlurped.result.extractorData.data.group[it].text.each { firstNested ->
                firstNested.each { secondNested ->
                    secondNested.each { finalValue ->
                        String property = objectProperties.get(i)
                        leagueTable."${property}".add(finalValue)
                    }
                }
            }
            i++
        }

    }
}

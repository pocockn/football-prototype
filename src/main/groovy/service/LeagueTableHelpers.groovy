package service

import groovy.json.JsonSlurper
import models.LeagueTable

class LeagueTableHelpers {

    JsonSlurper jsonSlurper = new JsonSlurper()
    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource("importJsonData.json").getFile());
    def jsonSlurped = jsonSlurper.parseText(file.text)
    ArrayList<String> objectProperties = ["teams", "played", "won", "draw", "lose", "goalDifference", "goalsFor", "goalsAgainst", "points"]
    List<String> jsonSelectors = ["Content", "Value 1", "Value 2", "Value 3", "Value 4", "Value 5", "Value 6", "Value 7", "Value 8"]
    LeagueTable leagueTable = new LeagueTable()


    public List<String> extractLeagueDataFromJson() {
        int i = 0
        jsonSelectors.each { jsonKey ->
            jsonSlurped.result.extractorData.data.group[jsonKey].text.each { firstNested ->
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

package service

import groovy.json.JsonSlurper
import models.LeagueTable

import javax.inject.Inject

class LeagueTableHelpers {

    ArrayList<String> objectProperties = ["teams", "played", "won", "draw", "lose", "goalDifference", "goalsFor", "goalsAgainst", "points"]
    List<String> jsonSelectors = ["Content", "Value 1", "Value 2", "Value 3", "Value 4", "Value 5", "Value 6", "Value 7", "Value 8"]

    @Inject
    JsonSlurper jsonSlurper
    @Inject
    LeagueTable leagueTable

    public List<String> extractLeagueDataFromJson(String jsonData) {
        Object parsedJson = jsonSlurper.parseText(jsonData)
        int i = 0
        jsonSelectors.each { jsonKey ->
            parsedJson.extractorData.data.group[jsonKey].text.each { firstNested ->
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

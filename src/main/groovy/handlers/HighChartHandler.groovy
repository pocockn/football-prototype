package handlers

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j
import models.Player
import models.Team
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.playerServices.TeamContent

import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class HighChartHandler extends InjectionHandler {

    void handle(Context ctx, Team team, ObjectMapper objectMapper, TeamContent teamContent) {
        Player player1 = new Player(name: 'Nick', goals: [0, 2, 3, 5, 6], ratings: [1, 2, 4, 8, 7, 5], manOfTheMatches: 10, cleanSheets: 20)
        Player player2 = new Player(name: 'Pasty', goals: [0, 5, 7, 9, 10], ratings: [1, 3, 4, 3, 9, 5], manOfTheMatches: 20, cleanSheets: 30)
        team.players.add(player1)
        team.players.add(player2)
        def json = writeObjectToJson(objectMapper, team)
        String formattedJson = formatJsonForHighChartsConsumption(json)
        teamContent.findHighestAverageRating(team.players).then { highestRatedPlayer ->
            teamContent.findObjectwithLargestSpecificProperty(team.players,"name", "manOfTheMatches").then { mostMotm ->
                teamContent.findObjectwithLargestSpecificProperty(team.players,"name", "cleanSheets").then { mostCleanSheets ->
                    ctx.render(handlebarsTemplate('highchartTest.html',
                            model: formattedJson,
                            highestRating: highestRatedPlayer,
                            mostMotm: mostMotm,
                            mostCleanSheets: mostCleanSheets))
                }
            }

        }
    }

    private static String writeObjectToJson(ObjectMapper objectMapper, Team team) {
        objectMapper.writeValueAsString(team)
    }

    private static String formatJsonForHighChartsConsumption(String json) {
        json.substring(1, json.length() - 1)
    }
    }

class HomeTeamContent {

}

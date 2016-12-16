package handlers

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j
import models.Player
import models.PlayerStatistics
import models.Team
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.FindLargestPropertyValues
import service.playerServices.TeamContent

import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class DashboardHandler extends InjectionHandler {

    void handle(Context ctx, Team team, ObjectMapper objectMapper, TeamContent teamContent, PlayerStatistics playerStatistics) {
        Player player1 = new Player(name: 'Nick', goals: [0, 2, 3, 5, 6], ratings: [1, 2, 4, 8, 7, 5], manOfTheMatches: 10, cleanSheets: 20)
        Player player2 = new Player(name: 'Pasty', goals: [0, 5, 7, 9, 10], ratings: [1, 3, 4, 3, 9, 5], manOfTheMatches: 20, cleanSheets: 30)
        FindLargestPropertyValues findLargestPropertyValues = new FindLargestPropertyValues(playerStatistics.propertiesToAnalyse)
        team.players.add(player1)
        team.players.add(player2)
        def json = writeObjectToJson(objectMapper, team)
        String formattedJson = formatJsonForHighChartsConsumption(json)
        teamContent.findHighestAverageRating(team.players).then { highestRatedPlayer ->
            findLargestPropertyValues.findLargestPropertyValues(team.players, "name").then { playerStatsMap ->
                ctx.render(handlebarsTemplate('dashboard.html',
                        model: formattedJson,
                        highestRating: highestRatedPlayer,
                        mostMotm: playerStatsMap))
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


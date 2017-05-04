package handlers

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j
import models.Player
import models.PlayersContainerHighChartAdapter
import models.TeamContainer
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.persistance_service.PlayerStoreService
import service.persistance_service.TeamStoreService
import service.player_services.FindPropertyStatistics
import service.player_services.TeamContent

import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class DashboardHandler extends InjectionHandler {

    void handle(Context ctx, ObjectMapper objectMapper, PlayerStoreService playerStoreService, TeamContent teamContent,
                FindPropertyStatistics findPropertyStatistics, TeamStoreService teamStoreService) {
        String id = ctx.allPathTokens['teamId']
        String teamName = extractTeamName(ctx)
        if (id) {
            teamStoreService.fetchPlayerIds(id).flatMap { playerIds ->
                playerStoreService.fetchByIds(playerIds)
            }.then { players ->
                formatJsonRenderTemplate(ctx, players, teamContent, objectMapper, findPropertyStatistics, teamName)
            }
        } else {
            playerStoreService.fetchAll().then { players ->
                formatJsonRenderTemplate(ctx, players, teamContent, objectMapper, findPropertyStatistics)

            }
        }

    }

    private String extractTeamName(Context ctx) {
        String teamName
        Optional<TeamContainer> teamOptional = ctx.maybeGet(TeamContainer.class)
        if (teamOptional.present) {
            teamName = teamOptional.get().team.name
        }
        teamName
    }

    private
    static void formatJsonRenderTemplate(Context ctx, List<Player> players, TeamContent teamContent,
                                         ObjectMapper objectMapper, FindPropertyStatistics findPropertyStatistics, String teamName = null) {
        PlayersContainerHighChartAdapter playersContainerHighChartAdapter = new PlayersContainerHighChartAdapter(players)
        String json = mapObjectToJson(objectMapper, playersContainerHighChartAdapter)
        String jsonOb = formatJsonForHighChartsConsumption(json)
        teamContent.findHighestAverageRating(players).then { highestRatedPlayer ->
            log.info("highest rated player $highestRatedPlayer")
            findPropertyStatistics.findLargestPropertyValues(players, "name").then { playerStatsMap ->
                renderTemplate(ctx, jsonOb, highestRatedPlayer, playerStatsMap, teamName)
            }
        }
    }

    static Map<String, Map<String, ?>> renameMapKeys(Map<String, Map<String, ?>> originalMap) {
        String[] stats = ["manOfTheMatches", "cleanSheets", "assists"]
        String[] renamedStats = ["Man Of The Matches", "Clean Sheets", "Assists"]
        int i = 0
        stats.each { stat ->
            def motm = originalMap.get(stat)
            originalMap.put(renamedStats[i], motm)
            originalMap.remove(stat)
            i++
        }
        originalMap
    }

    private static String mapObjectToJson(ObjectMapper objectMapper, PlayersContainerHighChartAdapter players) {
        objectMapper.writeValueAsString(players)
    }


    private static String formatJsonForHighChartsConsumption(String json) {
        json.substring(1, json.length() - 1)
    }

    private
    static renderTemplate(Context ctx, String jsonOb, Map<String, Double> highestRatedPlayer, Map<String, Map<String, ?>> playerStatsMap, String teamName = null) {
        ctx.render(handlebarsTemplate('dashboard.html',
                model: jsonOb,
                teamName: teamName,
                highestRating: highestRatedPlayer,
                mostMotm: renameMapKeys(playerStatsMap)))
    }
}


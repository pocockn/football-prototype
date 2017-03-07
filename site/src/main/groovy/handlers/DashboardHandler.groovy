package handlers

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j
import models.PlayersContainerHighChartAdapter
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.persistance_service.PlayerStoreService
import service.player_services.FindPropertyStatistics
import service.player_services.TeamContent

import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class DashboardHandler extends InjectionHandler {

    void handle(Context ctx, ObjectMapper objectMapper, PlayerStoreService playerStoreService, TeamContent teamContent,
                FindPropertyStatistics findPropertyStatistics) {
        playerStoreService.fetchAll().then { players ->
            PlayersContainerHighChartAdapter playersContainerHighChartAdapter = new PlayersContainerHighChartAdapter(players)
            String json = writeObjectToJson(objectMapper, playersContainerHighChartAdapter)
            String jsonOb = formatJsonForHighChartsConsumption(json)
            log.info("string jsonOB is ${jsonOb}")
            teamContent.findHighestAverageRating(players).then { highestRatedPlayer ->
                findPropertyStatistics.findLargestPropertyValues(players, "name").then { playerStatsMap ->
                    ctx.render(handlebarsTemplate('dashboard.html',
                            model: jsonOb,
                            highestRating: highestRatedPlayer,
                            mostMotm: renameMapKeys(playerStatsMap)))
                }

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


    private static String writeObjectToJson(ObjectMapper objectMapper, PlayersContainerHighChartAdapter players) {
        objectMapper.writeValueAsString(players)
    }

    private static String formatJsonForHighChartsConsumption(String json) {
        json.substring(1, json.length() - 1)
    }
}


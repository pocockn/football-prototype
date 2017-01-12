package handlers

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j
import models.PlayersContainer
import models.TeamContainer
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.persistance_service.StoreService
import service.player_services.FindPropertyStatistics
import service.player_services.TeamContent

import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class DashboardHandler extends InjectionHandler {

    void handle(Context ctx, ObjectMapper objectMapper, TeamContent teamContent, FindPropertyStatistics findPropertyStatistics, StoreService teamStoreService) {
        teamStoreService.fetchById('0000-0000-0000-0001').then { TeamContainer soldiersContent ->
            log.info("Fetching dashboard data for : ${soldiersContent.team.name}")
            TeamContainer goalsView = new TeamContainer(playersContainer: soldiersContent.playersContainer)
            String json = writeObjectToJson(objectMapper, goalsView.playersContainer)
            String jsonOb = formatJsonForHighChartsConsumption(json)
            teamContent.findHighestAverageRating(soldiersContent.playersContainer.players).then { highestRatedPlayer ->
                findPropertyStatistics.findLargestPropertyValues(soldiersContent.playersContainer.players, "name").then { playerStatsMap ->
                    ctx.render(handlebarsTemplate('dashboard.html',
                            model: jsonOb,
                            highestRating: highestRatedPlayer,
                            mostMotm: playerStatsMap))
                }

            }
        }
    }

    private static String writeObjectToJson(ObjectMapper objectMapper, PlayersContainer team) {
        objectMapper.writeValueAsString(team)
    }

    private static String formatJsonForHighChartsConsumption(String json) {
        json.substring(1, json.length() - 1)
    }
}


package handlers.team_handlers

import groovy.util.logging.Slf4j
import models.Team
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.persistance_service.PlayerStoreService
import service.persistance_service.TeamStoreService
import service.team.service.TeamContainerService

import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class TeamPlayersHandler extends InjectionHandler {
    void handle(Context ctx, TeamStoreService teamStoreService, PlayerStoreService playerStoreService, TeamContainerService teamContainerService) {
        Team team = teamContainerService.extractTeamDetails(ctx)
        String id = ctx.allPathTokens['teamId']
        teamStoreService.fetchPlayerIds(id)
                .onError { e ->
            log.info("exception finding players ${e}")
            ctx.render handlebarsTemplate("error.html")
        }.then { playerIds ->
            playerStoreService.fetchByIds(playerIds).then { players ->
                ctx.render handlebarsTemplate("allPlayers.html", model: players, teamId: id, teamName: team.name, single: true)
            }
        }
    }
}

package handlers.team_handlers

import groovy.util.logging.Slf4j
import models.Team
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.persistance_service.PlayerStoreService
import service.team.service.TeamContainerService

import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class TeamSinglePlayerHandler extends InjectionHandler {
    void handle(Context ctx, PlayerStoreService playerStoreService, TeamContainerService teamContainerService) {
        Team team = teamContainerService.extractTeamDetails(ctx)
        String playerId = ctx.pathTokens['id']
        playerStoreService.fetchById(playerId)
                .onError { e ->
            log.info("exception finding players ${e}")
            ctx.render handlebarsTemplate("error.html")
        }.then { player ->
            ctx.render handlebarsTemplate("singlePlayer.html", model: player, teamName: team.name, single: true, teamId: team.id)
        }
    }
}

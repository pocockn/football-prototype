package handlers.team_handlers

import groovy.util.logging.Slf4j
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.persistance_service.PlayerStoreService
import service.persistance_service.TeamStoreService

import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class TeamPlayersHandler extends InjectionHandler {
    void handle(Context ctx, TeamStoreService teamStoreService, PlayerStoreService playerStoreService) {
        String id = ctx.pathTokens['teamId']
        teamStoreService.fetchPlayerIds(id)
                .onError { e ->
            log.info("exception finding players ${e}")
            ctx.render handlebarsTemplate("error.html")
        }.then { playerIds ->
            playerStoreService.fetchByIds(playerIds).then { players ->
                ctx.render handlebarsTemplate("allPlayers.html", model: players)
            }
        }
    }
}

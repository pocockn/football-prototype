package handlers.team_handlers

import groovy.util.logging.Slf4j
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.persistance_service.PlayerStoreService
import service.persistance_service.TeamStoreService

import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class TeamSinglePlayerHandler extends InjectionHandler {
    void handle(Context ctx, PlayerStoreService playerStoreService) {
        String id = ctx.allPathTokens['teamId']
        String playerId = ctx.pathTokens['id']
        playerStoreService.fetchById(playerId)
                .onError { e ->
            log.info("exception finding players ${e}")
            ctx.render handlebarsTemplate("error.html")
        }.then { player ->
            ctx.render handlebarsTemplate("singlePlayer.html", model: player)
        }
    }
}

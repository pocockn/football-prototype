package handlers

import groovy.util.logging.Slf4j
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.persistance_service.PlayerStoreService

import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class AllPlayersHandler extends InjectionHandler {
    void handle(Context ctx, PlayerStoreService playerStoreService) {
        playerStoreService.fetchAll()
                .onError { e ->
            log.info("exception finding players ${e}")
            ctx.render handlebarsTemplate("error.html")
        }.then { players ->
            ctx.render handlebarsTemplate("allPlayers.html", model: players)
        }
    }
}

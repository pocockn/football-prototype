package api

import groovy.util.logging.Slf4j
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.persistance_service.PlayerStoreService

import static ratpack.jackson.Jackson.json

@Slf4j
class SinglePlayerApiHandler extends InjectionHandler {
    void handle(Context ctx, PlayerStoreService playerStoreService) {
        def id = ctx.pathTokens["id"]
        log.info("Player id fetched : ${id}")
        playerStoreService.fetchById(id).then { player ->
            ctx.render json(player)
        }
    }
}

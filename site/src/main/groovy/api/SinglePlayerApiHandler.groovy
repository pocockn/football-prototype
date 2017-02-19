package api

import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.persistance_service.PlayerStoreService

import static ratpack.jackson.Jackson.json

class SinglePlayerApiHandler extends InjectionHandler {
    void handle(Context ctx, PlayerStoreService playerStoreService) {
        def id = ctx.pathTokens["id"]
        playerStoreService.fetchById(id).then { player ->
            ctx.render json(player)
        }
    }
}

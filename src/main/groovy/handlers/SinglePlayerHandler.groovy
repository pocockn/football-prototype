package handlers

import models.Player
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.persistance_service.PlayerStoreService

import static ratpack.handlebars.Template.handlebarsTemplate

class SinglePlayerHandler extends InjectionHandler {
    void handle(Context context, PlayerStoreService playerStoreService) {
        def id = context.pathTokens["id"]
        playerStoreService.fetchById(id).then { Player playerSingle ->
            context.render handlebarsTemplate("singlePlayer.html", model: playerSingle)
        }
    }
}

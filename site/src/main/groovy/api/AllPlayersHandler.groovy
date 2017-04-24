package api

import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.persistance_service.PlayerStoreService

import static ratpack.jackson.Jackson.json

class AllPlayersHandler extends InjectionHandler {
    void handle(Context context, PlayerStoreService playerStoreService) {
        playerStoreService.fetchAll().then {
            context.render json(it)
        }
    }
}



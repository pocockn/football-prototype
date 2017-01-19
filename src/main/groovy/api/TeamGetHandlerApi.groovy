package api

import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.persistance_service.TeamStoreService

import static ratpack.jackson.Jackson.json

class TeamGetHandlerApi extends InjectionHandler {
    void handle(Context ctx, TeamStoreService teamStoreService) {
        teamStoreService.fetchAll().then {
            ctx.render json(it)
        }
    }
}

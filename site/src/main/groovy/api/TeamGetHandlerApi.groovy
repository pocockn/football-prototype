package api

import groovy.util.logging.Slf4j
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.persistance_service.TeamStoreService

import static ratpack.jackson.Jackson.json
@Slf4j
class TeamGetHandlerApi extends InjectionHandler {
    void handle(Context ctx, TeamStoreService teamStoreService) {
        teamStoreService.fetchAll().then {
            log.info("retrieved data via react")
            ctx.render json(it)
        }
    }
}

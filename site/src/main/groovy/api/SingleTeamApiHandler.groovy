package api

import groovy.util.logging.Slf4j
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.persistance_service.TeamStoreService

import static ratpack.jackson.Jackson.json

@Slf4j
class SingleTeamApiHandler extends InjectionHandler {
    void handle(Context ctx, TeamStoreService teamStoreService) {
        def id = ctx.pathTokens["id"]
        log.info("Team id fetched : ${id}")
        teamStoreService.fetchById(id).then { player ->
            ctx.render json(player)
        }
    }
}

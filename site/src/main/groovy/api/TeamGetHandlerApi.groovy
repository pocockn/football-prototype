package api

import groovy.util.logging.Slf4j
import models.Team
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import ratpack.jackson.Jackson
import service.persistance_service.TeamStoreService

import static ratpack.jackson.Jackson.json

@Slf4j
class TeamGetHandlerApi extends InjectionHandler {
    void handle(Context ctx, TeamStoreService teamStoreService) {

        ctx.byMethod {
            it.get {
                teamStoreService.fetchAll().then {
                    ctx.render json(it)
                }
            }
            it.post {
                ctx.parse(Jackson.fromJson(Team)).then { data ->
                    String teamId = data.id
                    teamStoreService.delete(teamId).then {
                        log.info("deleted ${it}")
                    }
                    ctx.response.status(201).send()
                }
            }
        }
    }
}

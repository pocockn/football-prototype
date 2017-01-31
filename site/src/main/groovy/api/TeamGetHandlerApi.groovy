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
                    log.info("retrieved data via react")
                    ctx.render json(it)
                }
            }
            it.post {
                log.info("post received from react app")
                ctx.parse(Jackson.fromJson(Team)).then { data ->
                    log.info("${data}")
                    ctx.response.status(201).send()
                }
            }
        }
    }
}

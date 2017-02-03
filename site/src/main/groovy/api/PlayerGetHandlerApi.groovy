package api

import groovy.util.logging.Slf4j
import models.Player
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import ratpack.jackson.Jackson
import service.persistance_service.PlayerStoreService
import service.persistance_service.TeamStoreService

import static ratpack.jackson.Jackson.json

@Slf4j
class PlayerGetHandlerApi extends InjectionHandler {
    void handle(Context ctx, TeamStoreService teamStoreService, PlayerStoreService playerStoreService) {

        ctx.byMethod {
            it.get {
                teamStoreService.fetchPlayers().then {
                    log.info("retrieved data via react")
                    ctx.render json(it)
                }
            }
            it.post {
                log.info("post received from react app")
                ctx.parse(Jackson.fromJson(Player)).then { data ->
                    playerStoreService.save(data).then {
                        log.info("${data} being recieved from react app with id of ${data.id}, name of ${data.name} and team of ${data.teamName}")
                        ctx.response.status(201).send()
                    }
                }
            }
        }
    }
}

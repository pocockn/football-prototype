package api

import groovy.util.logging.Slf4j
import models.Player
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import ratpack.jackson.Jackson
import service.persistance_service.PlayerStoreService

import static ratpack.jackson.Jackson.json

@Slf4j
class PlayerGetHandlerApi extends InjectionHandler {
    void handle(Context context, PlayerStoreService playerStoreService) {
        context.byMethod {
            it.get {
                playerStoreService.fetchAll().then {
                    log.info("retrieved data via react")
                    context.render json(it)
                }
            }
            it.post {
                log.info("post received from react app")
                context.parse(Jackson.fromJson(Player)).then { data ->
                    playerStoreService.save(data).then {
                        log.info("${data} being recieved from react app with id of ${data.id}, name of ${data.name} and team of ${data.teamName}")
                        context.response.status(201).send()
                    }
                }
            }
        }
    }
}

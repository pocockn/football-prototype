package api

import groovy.util.logging.Slf4j
import models.Player
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import ratpack.jackson.Jackson
import service.persistance_service.PlayerStoreService

@Slf4j
class PlayerCreateHandler extends InjectionHandler {
    void handle(Context context, PlayerStoreService playerStoreService) {
        context.byMethod {
            it.post {
                context.parse(Jackson.fromJson(Player)).then { data ->
                    playerStoreService.save(data).then {
                        log.info("Id of ${data.id}, name of ${data.name} and team of ${data.teamName}")
                        context.response.status(201).send()
                    }
                }
            }
        }
    }
}

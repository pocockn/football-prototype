package api

import groovy.util.logging.Slf4j
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import ratpack.jackson.Jackson
import service.persistance_service.PlayerStoreService

@Slf4j
class PlayerRemoveHandlerApi extends InjectionHandler {
    void handle(Context context, PlayerStoreService playerStoreService) {
        context.byMethod {
            it.post {
                log.info("post received from react app")
                context.parse(Jackson.fromJson(Object)).then { singlePlayer ->
                    log.info("${singlePlayer}")
                    String playerId = singlePlayer
                    log.info("About to delete ${singlePlayer}")
                    playerStoreService.delete(playerId).then {
                        log.info("deleted yo")
                    }
                    context.response.status(201).send()
                }
            }
        }
    }
}
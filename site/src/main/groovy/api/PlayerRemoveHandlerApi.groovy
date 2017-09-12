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
            it.delete {
                context.parse(Jackson.fromJson(Object)).then { singlePlayer ->
                    String playerId = singlePlayer
                    playerStoreService.delete(playerId).onError {
                        context.response.status(500).send()
                    }.then {
                        log.info("Deleted player: ${playerId}")
                        context.response.status(201).send()
                    }
                }
            }
        }
    }
}
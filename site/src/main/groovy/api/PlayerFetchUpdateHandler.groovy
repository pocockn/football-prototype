package api

import groovy.util.logging.Slf4j
import models.Player
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import ratpack.jackson.Jackson
import service.persistance_service.PlayerStoreService

import static ratpack.jackson.Jackson.json

@Slf4j
class PlayerFetchUpdateHandler extends InjectionHandler {
    void handle(Context context, PlayerStoreService playerStoreService) {
        context.byMethod {
            it.get {
                playerStoreService.fetchAll().then {
                    context.render json(it)
                }
            }
            it.post {
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

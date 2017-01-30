package api

import groovy.json.JsonSlurper
import groovy.util.logging.Slf4j
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.persistance_service.TeamStoreService

import static ratpack.jackson.Jackson.json
import static ratpack.jackson.Jackson.jsonNode

@Slf4j
class TeamGetHandlerApi extends InjectionHandler {
    void handle(Context ctx, TeamStoreService teamStoreService) {
        JsonSlurper jsonSlurper = new JsonSlurper()
        ctx.byMethod {
            it.get {
                teamStoreService.fetchAll().then {
                    log.info("retrieved data via react")
                    ctx.render json(it)
                }
            }
            it.post {

                log.info("post received from react app")
                def postBody = ctx.parse jsonNode()
                postBody.then {
                    log.info("${it}")
                }
                ctx.render ("${postBody}")
            }
        }
    }
}

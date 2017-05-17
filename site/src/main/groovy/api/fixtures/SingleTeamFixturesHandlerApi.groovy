package api.fixtures

import groovy.util.logging.Slf4j
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.persistance_service.TeamStoreService

import static ratpack.jackson.Jackson.json

@Slf4j
class SingleTeamFixturesHandlerApi extends InjectionHandler {

    void handle(Context context, TeamStoreService teamStoreService) {
        def id = context.allPathTokens["id"]
        teamStoreService.fetchFixtures(id).onError { e ->
            log.info("exception finding fixtures ${e}")
            context.response.status(400).send()
        }.then { fixtures ->
            context.render json(fixtures)
        }
    }
}

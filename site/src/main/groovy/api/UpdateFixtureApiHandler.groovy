package api

import groovy.util.logging.Slf4j
import models.Fixtures
import models.Match
import models.TeamContainer
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import ratpack.jackson.Jackson
import service.persistance_service.TeamStoreService

@Slf4j
class UpdateFixtureApiHandler extends InjectionHandler {

    void handle(Context context, TeamStoreService teamStoreService, Fixtures fixtures) {
        context.byMethod {
            // TODO: use real team ID
            def id = '0000-0000-0000-0001'
            it.post {
                context.parse(Jackson.fromJson(Object)).then { matches ->
                    teamStoreService.fetchById(id).map { TeamContainer teamContainer ->
                        teamContainer.fixtures.matches.addAll(matches as List<Match>)
                        teamContainer
                    }.map { teamContainer ->
                        teamStoreService.save(teamContainer)
                    }.then {
                        context.response.status(201).send()
                    }
                }
            }
        }
    }

}


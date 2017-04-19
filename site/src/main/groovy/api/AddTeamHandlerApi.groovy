package api

import groovy.util.logging.Slf4j
import models.Team
import models.TeamContainer
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.persistance_service.TeamStoreService

import static ratpack.jackson.Jackson.fromJson

@Slf4j
class AddTeamHandlerApi extends InjectionHandler {
    void handle(Context context, TeamStoreService teamStoreService) {
        context.byMethod {
            it.post {
                context.parse(fromJson(Team)).then { team ->
                    TeamContainer teamContainer = new TeamContainer(id: team.id, team: team)
                    teamStoreService.save(teamContainer).then {
                        context.response.status(201).send()
                    }
                }
            }
        }
    }
}

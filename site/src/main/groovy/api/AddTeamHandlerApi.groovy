package api

import groovy.util.logging.Slf4j
import models.Team
import models.TeamContainer
import ratpack.exec.Promise
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.persistance_service.TeamStoreService

import javax.validation.Validator

import static ratpack.jackson.Jackson.fromJson

@Slf4j
class AddTeamHandlerApi extends InjectionHandler {
    void handle(Context context, TeamStoreService teamStoreService) {
        context.byMethod {
            it.post {
                context.parse(fromJson(Team))
                        .then { team ->
                    TeamContainer teamContainer = new TeamContainer(id: team.id, team: team)
                    teamStoreService
                            .save(teamContainer)
                            .then {
                        context.response.status(201).send()
                    }
                }
            }
        }
    }
//http://mrhaki.blogspot.co.uk/2015/11/ratpack-validating-forms.html
    // http://kyleboon.org/blog/2016/06/30/validating-an-http-post-with-ratpack/
    private <T> Promise<T> parseAndValidate(Context context, Class<T> type) {
        context.parse(fromJson(type)).route({ T obj ->
            context.get(Validator).validate(obj).size() > 0
        }, {

        })
    }
}

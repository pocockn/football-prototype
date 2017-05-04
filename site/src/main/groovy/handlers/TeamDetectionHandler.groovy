package handlers

import groovy.util.logging.Slf4j
import models.TeamContainer
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.persistance_service.TeamStoreService

@Slf4j
class TeamDetectionHandler extends InjectionHandler {
    void handle(Context context, TeamStoreService teamStoreService) {
        String id = context.allPathTokens['teamId']
        teamStoreService.fetchById(id).then { TeamContainer teamContainer ->
            context.request.add(TeamContainer.class, teamContainer)
            context.next()
        }
    }
}

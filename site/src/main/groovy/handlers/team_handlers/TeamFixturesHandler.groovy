package handlers.team_handlers

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j
import models.Team
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.persistance_service.TeamStoreService
import service.team.service.TeamContainerService

import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class TeamFixturesHandler extends InjectionHandler {
    void handle(Context ctx, TeamStoreService teamStoreService, ObjectMapper objectMapper, TeamContainerService teamContainerService) {
        Team team = teamContainerService.extractTeamDetails(ctx)
        String id = ctx.allPathTokens['teamId']
        teamStoreService.fetchFixtures(id)
                .onError { e ->
            log.info("exception finding players ${e}")
            ctx.render handlebarsTemplate("error.html")
        }.then { fixtures ->
            String json = objectMapper.writeValueAsString(fixtures.matches)
            ctx.render handlebarsTemplate("fixtures.html", calendar: json, teamName: team.name, single: true, teamId: team.id)
        }
    }
}

package handlers.team_handlers

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.persistance_service.TeamStoreService

import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class TeamFixturesHandler extends InjectionHandler {
    void handle(Context ctx, TeamStoreService teamStoreService, ObjectMapper objectMapper) {
        String id = ctx.allPathTokens['teamId']
        teamStoreService.fetchFixtures(id)
                .onError { e ->
            log.info("exception finding players ${e}")
            ctx.render handlebarsTemplate("error.html")
        }.then { fixtures ->
            String json = objectMapper.writeValueAsString(fixtures.matches)
            ctx.render handlebarsTemplate("fixtures.html", calendar: json)
        }
    }
}

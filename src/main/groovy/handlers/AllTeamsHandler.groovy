package handlers

import groovy.util.logging.Slf4j
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.TeamPersistanceService.TeamStoreService

import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class AllTeamsHandler extends InjectionHandler {
    void handle(Context ctx, TeamStoreService teamStoreService) {
        teamStoreService.fetchAll()
                .onError { e ->
            log.info("exception finding sessions ${e}")
            ctx.render handlebarsTemplate("error.html")
        }
        .then { teams ->
            ctx.render handlebarsTemplate("allTeams.html", model: [teams: teams])
        }
    }
}

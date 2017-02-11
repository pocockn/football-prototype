package handlers

import groovy.util.logging.Slf4j
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.persistance_service.TeamStoreService

import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class AllPlayersHandler extends InjectionHandler {
    void handle(Context ctx, TeamStoreService teamStoreService) {
        teamStoreService.fetchAll()
                .onError { e ->
            log.info("exception finding sessions ${e}")
            ctx.render handlebarsTemplate("error.html")
        }.then { teams ->
            log.info("Retrieved these player containers from the database : ${teams*.playersContainer.players}")
            ctx.render handlebarsTemplate("allPlayers.html", model: teams*.playersContainer.players)
        }
    }
}

package handlers

import groovy.util.logging.Slf4j
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.PersistanceService.StoreService

import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class AllTeamsHandler extends InjectionHandler {
    void handle(Context ctx, StoreService teamStoreService) {
        teamStoreService.fetchAll()
                .onError { e ->
            log.info("exception finding sessions ${e}")
            ctx.render handlebarsTemplate("error.html")
        }.then { teams ->
            log.info("Retrieved these teams from the database : ${teams*.id}")
            ctx.render handlebarsTemplate("allTeams.html", model: teams)
        }
    }
}

package handlers

import league.ImportClient
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.LeagueTableHelpers

import static ratpack.handlebars.Template.handlebarsTemplate

class LeagueTableHandler extends InjectionHandler {
    void handle(Context context, ImportClient importClient) {
//        def data = importClient.leagueDataExtractor()
        LeagueTableHelpers leagueTableHelpers = new LeagueTableHelpers()
        leagueTableHelpers.storeItemsInList()

        context.render handlebarsTemplate("league.html",
                model: leagueTableHelpers.leagueTable)
    }
}

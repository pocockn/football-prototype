package handlers

import groovy.util.logging.Slf4j
import league.ImportClient
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import service.LeagueTableHelpers
import wslite.rest.Response

import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class LeagueTableHandler extends InjectionHandler {
    void handle(Context context, ImportClient importClient, LeagueTableHelpers leagueTableHelpers) {
        Response data = importClient.leagueDataExtractor()
        leagueTableHelpers.extractLeagueDataFromJson(data.contentAsString)

        context.render handlebarsTemplate("league.html",
                model: leagueTableHelpers.leagueTable)
    }
}

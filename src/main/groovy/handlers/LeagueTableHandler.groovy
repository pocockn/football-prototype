package handlers

import league.ImportClient
import ratpack.handling.Context
import ratpack.handling.InjectionHandler

class LeagueTableHandler extends InjectionHandler {
    void handle(Context context, ImportClient importClient) {
        def data = importClient.leagueDataExtractor()
        context.render(data.getContentAsString())
    }
}

package handlers

import com.fasterxml.jackson.databind.ObjectMapper
import models.Player
import models.Team
import ratpack.handling.Context
import ratpack.handling.InjectionHandler

import static ratpack.handlebars.Template.handlebarsTemplate

class HighChartHandler extends InjectionHandler {

    void handle(Context ctx, Team team, ObjectMapper objectMapper) {
        Player player1 = new Player(name: 'Nick', goals: [0, 2, 3, 5, 6])
        Player player2 = new Player(name: 'Pasty', goals: [0, 5, 7, 9, 10])
        team.players.add(player1)
        team.players.add(player2)
        def json = writeObjectToJson(objectMapper, team)
        String formattedJson = formatJsonForHighChartsConsumption(json)
        ctx.render(handlebarsTemplate('highchartTest.html', model: formattedJson))
    }

    private static String writeObjectToJson(ObjectMapper objectMapper, Team team) {
        objectMapper.writeValueAsString(team)
    }

    private static String formatJsonForHighChartsConsumption(String json) {
        json.substring(1, json.length() - 1)
    }
}

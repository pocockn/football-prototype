package handlers

import models.TestDataJson
import ratpack.handling.Context
import ratpack.handling.InjectionHandler

import static ratpack.jackson.Jackson.json


class HighChartHandler extends InjectionHandler {

    void handle(Context ctx, TestDataJson testDataJson) {
        testDataJson.goals = 1000
        testDataJson.name = "Nick"
        ctx.render(json(testDataJson))
    }
}

package handlers

import ratpack.handling.Context
import ratpack.handling.Handler

import static ratpack.handlebars.Template.handlebarsTemplate

class FixturesHandler implements Handler {

    @Override
    void handle(Context ctx) throws Exception {
        ctx.render handlebarsTemplate("calendar.html")
    }
}

package handlers

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j
import models.Fixtures
import models.Match
import ratpack.handling.Context
import ratpack.handling.InjectionHandler

import java.time.ZoneOffset
import java.time.ZonedDateTime

import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class FixturesHandler extends InjectionHandler {

    void handle(Context ctx, Fixtures fixtures) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper()
        Match match = new Match(id: "123", title: "match one", start: ZonedDateTime.now(ZoneOffset.UTC).plusDays(5), end: ZonedDateTime.now(ZoneOffset.UTC).plusDays(5))
        Match match1 = new Match(id: "1234", title: "match two", start: ZonedDateTime.now(ZoneOffset.UTC).plusDays(7), end: ZonedDateTime.now(ZoneOffset.UTC).plusDays(5))
        fixtures.matches.add(match)
        fixtures.matches.add(match1)
        String json = objectMapper.writeValueAsString(fixtures.matches)
        log.info(json)
        ctx.render handlebarsTemplate("fixtures.html",
                calendar: json)
    }
}
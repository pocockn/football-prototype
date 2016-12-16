package handlers

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j
import models.Fixtures
import models.Match
import ratpack.handling.Context
import ratpack.handling.Handler

import java.time.ZoneOffset
import java.time.ZonedDateTime

import static ratpack.handlebars.Template.handlebarsTemplate
@Slf4j
class FixturesHandler implements Handler {

    @Override
    void handle(Context ctx) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper()

        List<Match> matches = []
        Match match = new Match(id: "123", title: "match one", start: ZonedDateTime.now(ZoneOffset.UTC).plusDays(5), end: ZonedDateTime.now(ZoneOffset.UTC).plusDays(5))
        Match match1 = new Match(id: "1234", title: "match two", start: ZonedDateTime.now(ZoneOffset.UTC).plusDays(7), end: ZonedDateTime.now(ZoneOffset.UTC).plusDays(5))
        matches.add(match1)
        matches.add(match)
        Fixtures fixtures = new Fixtures(matches)
        String json = objectMapper.writeValueAsString(fixtures.matches)
        log.info(json)
        ctx.render handlebarsTemplate("fixtures.html",
                calendar: json)
    }
}

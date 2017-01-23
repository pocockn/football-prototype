package calendar

import com.fasterxml.jackson.databind.ObjectMapper
import models.Fixtures
import models.Match
import spock.lang.Specification

import java.time.ZoneOffset
import java.time.ZonedDateTime

class CalendarJsonSpec extends Specification {

    void "calendar creates valid JSON confirming to all calendar JS rules"() {

        given:
        ObjectMapper objectMapper = new ObjectMapper()

        List<Match> matches = []
        Match match = new Match(id: "123", title: "match one", start: ZonedDateTime.now(ZoneOffset.UTC).plusDays(5), end: ZonedDateTime.now(ZoneOffset.UTC).plusDays(5))
        Match match1 = new Match(id: "1234", title: "match two", start: ZonedDateTime.now(ZoneOffset.UTC).plusDays(5), end: ZonedDateTime.now(ZoneOffset.UTC).plusDays(5))
        matches.add(match1)
        matches.add(match)
        Fixtures fixtures = new Fixtures(matches: matches)

        when:
        String json = objectMapper.writeValueAsString(fixtures.matches)

        then:
        json.contains('"id":"1234","title":"match two"')
        json.contains('"id":"123","title":"match one"')


    }
}

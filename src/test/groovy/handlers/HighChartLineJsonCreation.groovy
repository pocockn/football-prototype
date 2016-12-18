import com.fasterxml.jackson.databind.ObjectMapper
import models.Match
import models.Player
import models.Team
import spock.lang.Specification

import java.time.ZoneOffset
import java.time.ZonedDateTime

class HighChartLineJsonCreation extends Specification {

    def "Json created is what highchart needs to create graph"() {
        given:
        Match match = new Match(id: "123", title: "match one", start: ZonedDateTime.now(ZoneOffset.UTC).plusDays(5), end: ZonedDateTime.now(ZoneOffset.UTC).plusDays(5))
        Match match1 = new Match(id: "1234", title: "match two", start: ZonedDateTime.now(ZoneOffset.UTC).plusDays(5), end: ZonedDateTime.now(ZoneOffset.UTC).plusDays(5))
        List<Match> matches = []
        matches.add(match1)
        matches.add(match)
        Player player1 = new Player(name: 'Nick', goals: [0, 2, 3, 5, 6])
        Player player2 = new Player(name: 'Pasty', goals: [0, 5, 7, 9, 10])
        Team team = new Team(matches)
        team.players.add(player1)
        team.players.add(player2)

        ObjectMapper objectMapper = new ObjectMapper()

        when:
        String json = objectMapper.writeValueAsString(team)
        json.substring(1, json.length() - 1)

        then:
        json == """{"id":null,"fixtures":{"matches":null},"series":[{"name":"Nick","ratings":null,"manOfTheMatches":null,"cleanSheets":null,"data":[0,2,3,5,6]},{"name":"Pasty","ratings":null,"manOfTheMatches":null,"cleanSheets":null,"data":[0,5,7,9,10]}]}"""

    }
}

import com.fasterxml.jackson.databind.ObjectMapper
import models.*
import spock.lang.Ignore
import spock.lang.Specification

import java.time.ZoneOffset
import java.time.ZonedDateTime

class HighChartLineJsonCreation extends Specification {
    @Ignore
    def "Json created is what highchart needs to create graph"() {
        given:
        Match match = new Match(id: "123", title: "match one", start: ZonedDateTime.now(ZoneOffset.UTC).plusDays(5), end: ZonedDateTime.now(ZoneOffset.UTC).plusDays(5))
        Match match1 = new Match(id: "1234", title: "match two", start: ZonedDateTime.now(ZoneOffset.UTC).plusDays(5), end: ZonedDateTime.now(ZoneOffset.UTC).plusDays(5))
        List<Match> matches = []
        matches.add(match1)
        matches.add(match)
        Fixtures fixtures = new Fixtures(matches: matches)
        Player player1 = new Player(name: 'Nick', goals: [0, 2, 3, 5, 6])
        Player player2 = new Player(name: 'Pasty', goals: [0, 5, 7, 9, 10])
        List<Player> players = []
        players.add(player1)
        players.add(player2)
        PlayersContainer playersObject = new PlayersContainer(players: players)
        Team team = new Team(id: "123", name: "Shire Soldiers")

        TeamContainer teamContainer = new TeamContainer(id: "1234", team: team, fixtures: fixtures, playersContainer: playersObject)


        ObjectMapper objectMapper = new ObjectMapper()

        when:
        String json = objectMapper.writeValueAsString(teamContainer)
        json.substring(1, json.length() - 1)

        then:
        json == """{"id":"1234","team":{"id":"123","name":"Shire Soldiers"},"fixtures":{"matches":[{"id":"1234","title":"match two","start":"2017-01-13T11:17:28.324Z","end":"2017-01-13T11:17:28.324Z","className":"fc-event-primary","textColor":"black"},{"id":"123","title":"match one","start":"2017-01-13T11:17:28.309Z","end":"2017-01-13T11:17:28.314Z","className":"fc-event-primary","textColor":"black"}]},"playersContainer":{"series":[{"id":null,"name":"Nick","ratings":null,"assists":null,"manOfTheMatches":null,"cleanSheets":null,"data":[0,2,3,5,6]},{"id":null,"name":"Pasty","ratings":null,"assists":null,"manOfTheMatches":null,"cleanSheets":null,"data":[0,5,7,9,10]}],"players":[{"id":null,"name":"Nick","ratings":null,"assists":null,"manOfTheMatches":null,"cleanSheets":null,"data":[0,2,3,5,6]},{"id":null,"name":"Pasty","ratings":null,"assists":null,"manOfTheMatches":null,"cleanSheets":null,"data":[0,5,7,9,10]}]}}"""

    }
}

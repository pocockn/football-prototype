import com.fasterxml.jackson.databind.ObjectMapper
import models.Player
import models.Team
import spock.lang.Specification

class HighChartLineJsonCreation extends Specification {

    def "Json created is what highchart needs to create graph"() {
        given:
        Player player1 = new Player(name: 'Nick', goals: [0, 2, 3, 5, 6])
        Player player2 = new Player(name: 'Pasty', goals: [0, 5, 7, 9, 10])
        Team team = new Team()
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

package service

import models.Player
import ratpack.test.exec.ExecHarness
import spock.lang.AutoCleanup
import spock.lang.Specification

class PlayerManOfTheMatchServiceImplGenericSpec extends Specification {

    @AutoCleanup
    ExecHarness execHarness = ExecHarness.harness()

    void "find player statistics from a list of players and properties"() {
        given:
        List<String> propertyNames = ['manOfTheMatches', 'cleanSheets']
        FindLargestPropertyValues playerManOfTheMatchService = new FindLargestPropertyValues(propertyNames)
        ArrayList<Player> players = new ArrayList<>()
        Player player = new Player(name: "Nick", ratings: [1, 2, 4, 5, 6], goals: [1, 4, 6, 2, 4], manOfTheMatches: 10, cleanSheets: 12)
        Player player1 = new Player(name: "Pasty", ratings: [1, 5, 8, 9, 6], goals: [1, 4, 6, 2, 4], manOfTheMatches: 24, cleanSheets: 25)
        players.add(player)
        players.add(player1)

        when:
        def expectedValue = execHarness.yield {
            playerManOfTheMatchService.findLargestPropertyValues(players, "name")
        }

        then:
        expectedValue.getValue().toString() == "[manOfTheMatches:[Pasty:25], cleanSheets:[Pasty:25]]"

    }

}

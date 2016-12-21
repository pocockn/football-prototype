package service

import models.Player
import ratpack.test.exec.ExecHarness
import service.playerServices.FindPropertyStatistics
import service.playerServices.FindPropetyStatisticsImpl
import spock.lang.AutoCleanup
import spock.lang.Specification

class FindLargestPropertyValuesSpec extends Specification {

    @AutoCleanup
    ExecHarness execHarness = ExecHarness.harness()

    void "find player statistics from a list of players and properties"() {
        given:
        FindPropertyStatistics findPropetyStatistics = new FindPropetyStatisticsImpl()
        ArrayList<Player> players = new ArrayList<>()
        Player player = new Player(name: "Nick", ratings: [1, 2, 4, 5, 6], goals: [1, 4, 6, 2, 4], manOfTheMatches: 26, cleanSheets: 12, assists: 25)
        Player player1 = new Player(name: "Pasty", ratings: [1, 5, 8, 9, 6], goals: [1, 4, 6, 2, 4], manOfTheMatches: 24, cleanSheets: 25, assists: 30)
        players.add(player)
        players.add(player1)

        when:
        def expectedValue = execHarness.yield {
            findPropetyStatistics.findLargestPropertyValues(players, "name")
        }

        then:
        expectedValue.getValue().toString() == "[manOfTheMatches:[Nick:26], cleanSheets:[Pasty:25], assists:[Pasty:30]]"

    }

}

package service

import models.Player
import ratpack.test.exec.ExecHarness
import service.playerServices.TeamContent
import service.playerServices.TeamContentImpl
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class PlayerManOfTheMatchServiceImplSpec extends Specification {

    @AutoCleanup
    ExecHarness execHarness = ExecHarness.harness()

    @Subject
    @Shared
    TeamContent playerManOfTheMatchService = new TeamContentImpl()

    void "find player with the most man of the matches"() {
        given:
        ArrayList<Player> players = new ArrayList<>()
        Player player = new Player(name: "Nick", ratings: [1, 2, 4, 5, 6], goals: [1, 4, 6, 2, 4], manOfTheMatches: 10)
        Player player1 = new Player(name: "Pasty", ratings: [1, 5, 8, 9, 6], goals: [1, 4, 6, 2, 4], manOfTheMatches: 24)
        players.add(player)
        players.add(player1)

        when:
        def expectedValue = execHarness.yield {
            playerManOfTheMatchService.findObjectwithLargestSpecificProperty(players, "name", "manOfTheMatches")
        }

        then:
        expectedValue.getValue().toString() == "[Pasty:24]"

    }

}

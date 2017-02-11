package service

import models.Player
import ratpack.test.exec.ExecHarness
import service.player_services.TeamContent
import service.player_services.TeamContentImpl
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class PlayerRatingsServiceImplSpec extends Specification {

    @AutoCleanup
    ExecHarness execHarness = ExecHarness.harness()

    @Subject
    @Shared
    TeamContent playerRatingsService = new TeamContentImpl()

    void "Calculate average player ratings from a list of integers"() {
        given:
        ArrayList<Player> players = new ArrayList<>()
        Player player = new Player(name: "Nick", ratings: [1, 2, 4, 5, 6], seasonGoals: [1, 4, 6, 2, 4])
        Player player1 = new Player(name: "Pasty", ratings: [1, 5, 8, 9, 6], seasonGoals: [1, 4, 6, 2, 4])
        players.add(player)
        players.add(player1)

        when:
        def expectedValue = execHarness.yield {
            playerRatingsService.findHighestAverageRating(players)
        }

        then:
        expectedValue.getValue().toString() == "[Pasty:5.8]"

    }

}

package service

import models.Player
import service.player_services.TeamContent
import service.player_services.TeamContentImpl
import spock.lang.Specification

class FindTotalGoalsSpec extends Specification {


    void "find total goals from a player"() {
        given:
        TeamContent teamContent = new TeamContentImpl()
        Player player = new Player(name: "Nick", ratings: [1, 2, 4, 5, 6], goals: [1, 4, 6, 2, 4], manOfTheMatches: 26, cleanSheets: 12, assists: 25)

        when:
        def expectedValue = teamContent.findGoalTotal(player.goals)

        then:
        expectedValue == 17

    }
}

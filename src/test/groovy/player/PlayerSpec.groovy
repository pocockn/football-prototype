package player

import models.Player
import spock.lang.Specification

class PlayerSpec extends Specification {
    void "check finish season method resets correct variables"() {
        given:
        Player player = new Player(
                id: "123",
                name: "Nick Pocock",
                teamName: "Shire Soldiers",
                bio: "this is a bio",
                seasonGoals: [4, 2, 2],
                ratings: [1, 9],
                assists: 4,
                manOfTheMatches: 2,
                cleanSheets: 4,
                totalGoals: 6
        )
        when:
        player.finishSeason()

        then:
        player.seasonGoals == []
        player.totalGoals == 6
        player.cleanSheets == 0
    }
}

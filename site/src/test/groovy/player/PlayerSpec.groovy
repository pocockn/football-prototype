package player

import spock.lang.Specification

import static fixture.TeamBootstrapper.teamContainer

class PlayerSpec extends Specification {
    void "check finish season method resets correct variables"() {
        given:
        def team = teamContainer {
            players {
                player {
                    id = "123"
                    name = "Nick Pocock"
                    teamName = "Shire Soldiers"
                    bio = "this is a bio"
                    seasonGoals = [4, 2, 2]
                    ratings = [1, 9]
                    assists = 4
                    manOfTheMatches = 2
                    cleanSheets = 4
                    totalGoals = 6
                }
            }
        }

        when:
        team.playersContainer.players[0].id = '1234'

        then:
        team.playersContainer.players[0].id == '1234'

        when:
        team.playersContainer.players[0].finishSeason()

        then:
        team.playersContainer.players[0].seasonGoals == []
        team.playersContainer.players[0].totalGoals == 6
        team.playersContainer.players[0].cleanSheets == 0
    }
}

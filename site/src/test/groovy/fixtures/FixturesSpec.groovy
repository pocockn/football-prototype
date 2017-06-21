package fixtures

import spock.lang.Specification

import static fixture.TeamBootstrapper.teamContainer

class FixturesSpec extends Specification {

    void "Ensure the fixtures builder brings back the correct values"() {
        given:
        def team = teamContainer {
            team {
                id = '124'
                name = "Shire Soldiers"
            }
            fixtures {
                match('123', 'New Match', '12/14/2016', '12/15/2017')
            }
        }

        when:
        team.fixtures.matches[0].id = '1234'

        then:
        team.fixtures.matches[0].id == '1234'
        team.team.name == "Shire Soldiers"

    }

}

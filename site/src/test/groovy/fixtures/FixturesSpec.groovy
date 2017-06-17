package fixtures

import fixture.TeamBootstrapper
import spock.lang.Specification

class FixturesSpec extends Specification {

    void "Ensure the fixtures builder brings back the correct values"() {
        given:
        TeamBootstrapper teamBootstrapper = new TeamBootstrapper()
        def team = teamBootstrapper.teamContainer {
            fixtures {
                match('123', 'New Match', '12/14/2016', '12/15/2017')
            }
        }

        when:
        team.fixtures.matches[0].id = '1234'

        then:
        team.fixtures.matches[0].id == '1234'

    }

}

package api.fixtures

import Fixture.FootballApplicationUnderTest
import spock.lang.Specification

class SingleTeamFixturesHandlerApiSpec extends Specification {
    void "test fixtures api endpoint returns fixtures"() {
        given:
        def aut = new FootballApplicationUnderTest()

        when:
        def response = aut.httpClient.get('api/fixtures/0000-0000-0000-0001/all').body.text
        response

        then:
        response.contains("""{"matches":[{"id":"1234","title":"match two","start":"2017-01-01T17:40:16.264Z","end":"2017-01-01T17:40:16.264Z","className":"fc-event-primary","textColor":"black"},{"id":"123","title":"match one","start":"2017-01-01T17:40:16.215Z","end":"2017-01-01T17:40:16.258Z","className":"fc-event-primary","textColor":"black"}]}""")
    }
}
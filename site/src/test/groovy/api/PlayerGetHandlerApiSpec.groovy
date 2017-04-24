package api

import Fixture.FootballApplicationUnderTest
import spock.lang.Specification

class PlayerGetHandlerApiSpec extends Specification {
    void "test player api handler returns players"() {
        given:
        def aut = new FootballApplicationUnderTest()

        when:
        def response = aut.httpClient.get('api/players/all').body.text

        then:
        response.contains("""[{"id":"0000-0000-0000-0006","name":"Nick Pocock","teamName":"Shire Soldiers""")
    }
}
package api

import Fixture.FootballApplicationUnderTest
import spock.lang.Specification

class PlayerGetHandlerApiSpec extends Specification {
    void "test player api handler returns players"() {
        given:
        def aut = new FootballApplicationUnderTest()

        when:
        def response = aut.httpClient.get('api/players/addGetPlayers').body.text

        then:
        response.contains("""[{"id":"2","name":"Connor Pasty","teamName":"Shire Soldiers""")
    }
}

package api

import Fixture.FootballApplicationUnderTest
import groovy.json.JsonBuilder
import spock.lang.AutoCleanup
import spock.lang.Specification

class ParseJsonViaApi extends Specification {

    @AutoCleanup
     aut = new FootballApplicationUnderTest()

    void "parse JSON via post from the /api/team end point"() {
        given:
        def jsonToSend = [id: "1", name: "Shire Soldiers"]
        JsonBuilder jsonBuilder = new JsonBuilder(jsonToSend)

        when:
        def response = aut.httpClient.requestSpec { spec ->
            spec.body { b ->
                b.type("application/json")
                b.text(jsonBuilder.toPrettyString())
            }
        }.post('api/teams')

        then:
        response.statusCode == 201
    }
}

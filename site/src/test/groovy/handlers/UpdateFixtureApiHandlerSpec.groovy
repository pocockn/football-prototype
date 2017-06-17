package handlers

import fixture.FootballApplicationUnderTest
import groovy.json.JsonBuilder
import spock.lang.AutoCleanup
import spock.lang.Specification

class UpdateFixtureApiHandlerSpec extends Specification {

    @AutoCleanup
            aut = new FootballApplicationUnderTest()

    void "parse JSON via the update fixtures end point within the api"() {
        given:
        def jsonToSend = [
                [
                        id   : '123',
                        title: 'matchTitle',
                        start: '2017-01-01T17:40:16.264Z',
                        end  : '2017-01-01T17:40:16.264Z',
                ],
                [
                        id   : '123',
                        title: 'matchTitle',
                        start: '2017-01-01T17:40:16.264Z',
                        end  : '2017-01-01T17:40:16.264Z',
                ]
        ]

        JsonBuilder jsonBuilder = new JsonBuilder(jsonToSend)

        when:
        def response = aut.httpClient.requestSpec { spec ->
            spec.body { b ->
                b.type("application/json")
                b.text(jsonBuilder.toPrettyString())
            }
        }.post('api/fixtures/0000-0000-0000-0001/save-fixtures')

        then:
        response.statusCode == 201
    }
}

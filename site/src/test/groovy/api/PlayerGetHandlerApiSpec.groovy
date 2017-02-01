package api

import Fixture.FootballApplicationUnderTest
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.groovy.test.handling.internal.DefaultGroovyRequestFixture
import ratpack.test.handling.HandlingResult
import ratpack.test.handling.RequestFixture
import spock.lang.Specification

class PlayerGetHandlerApiSpec extends Specification {
    void "test player api handler returns players"() {
        given:
        def aut = new FootballApplicationUnderTest()

        when:
        def response = aut.httpClient.get('api/players').body.text

        then:
        response == "xxx"
    }
}

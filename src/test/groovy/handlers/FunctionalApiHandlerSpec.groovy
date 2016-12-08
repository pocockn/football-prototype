package handlers

import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import spock.lang.AutoCleanup
import spock.lang.Specification

class FunctionalApiHandlerSpec extends Specification {

    @AutoCleanup
    def aut = new GroovyRatpackMainApplicationUnderTest()

    void "should properly render with get request to api handler"() {
        when:
        def response = aut.httpClient.get('api/highchart').body.text

        then:
        response.contains("This is a WIP highchart test")
    }
}

package browserTest

import geb.Page
import geb.spock.GebSpec
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest

class DashboardPage extends Page {
    static url = "http:localhost:5050/dashboard"

    static content = {
        heading { $("h1").text() }
    }
}

class DashboardSpec extends GebSpec {

    def aut = new GroovyRatpackMainApplicationUnderTest()

    def setup() {
        URI base = aut.address
        browser.baseUrl = base.toString()
    }


    def "go to dashboard page"() {
        when:
        to DashboardPage

        then:
        at DashboardPage
    }

}

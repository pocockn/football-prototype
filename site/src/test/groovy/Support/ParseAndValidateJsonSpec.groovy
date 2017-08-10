package Support

import spock.lang.PendingFeature
import spock.lang.Specification
import support.ParseAndValidateJson

class ParseAndValidateJsonSpec extends Specification {

    @PendingFeature
    def "Parse and a validate a JSON object"() {
        given:
        ParseAndValidateJson parseAndValidateJson = new ParseAndValidateJson()

        when:


        then:
        true

    }
}
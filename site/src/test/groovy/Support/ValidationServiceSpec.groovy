package Support

import spock.lang.Specification
import spock.lang.Unroll
import validation.ValidationService

import javax.validation.Validation

class ValidationServiceSpec extends Specification {

    def validator = Validation.buildDefaultValidatorFactory().validator

    @Unroll
    def "Validation service returns an object with a map of errors based on annotations"() {
        given:
        def validationService = new ValidationService(validator)
        TestObject object = new TestObject(email: email, rating: rating)

        when:
        def validated = validationService.validate(object)

        then:
        validated.errors == expected

        where:
        email             | rating | expected
        'notValidEmail'   | '12'   | [name: "Player name is null", email: "Not correct email"]
        'valid@email.com' | '12'   | [name: "Player name is null", email: "Not correct email"]
        'notValidEmail'   | null   | [name: "Player name is null", email: "Not correct email"]

    }
}

class TestObject {

    String playerName


    String email


    String rating
}


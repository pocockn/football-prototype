package validation

import javax.inject.Inject
import javax.validation.ConstraintViolation
import javax.validation.Validator


class ValidationService {

    private final Validator validator

    @Inject
    ValidationService(Validator validator) {
        this.validator = validator
    }

    public <T> Validated<T> validate(T object) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object)
        Map<String, String> errorMessages = constraintViolations.collectEntries { violation ->
            [(constraintViolations.propertyPath.toString()): constraintViolations.message]
        }
        new Validated<T>(object, errorMessages)
    }
}

package support

import java.util.function.Predicate

class Validated<T> {

    final T value

    final Map<String, String> errors

    Validated(T value, Map<String, String> errors) {
        this.value = value
        this.errors = errors
    }

    static Predicate<Validated<T>> isInvalid() {
        { Validated<T> validated ->
            !validated.errors.isEmpty()
        }
    }
}
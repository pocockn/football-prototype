package support

import ratpack.exec.Promise
import ratpack.handling.Context
import ratpack.jackson.Jackson

import static ratpack.jackson.Jackson.fromJson

class ParseAndValidateJson<T> {

    Promise<T> parseAndValidateJsonObject(Context context) {
        context.parse(fromJson(T))
    }
}
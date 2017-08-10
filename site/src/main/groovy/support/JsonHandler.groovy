package support

import ratpack.exec.Operation
import ratpack.handling.Context
import ratpack.handling.Handler
import ratpack.path.PathTokens

abstract class JsonHandler<T> implements Handler {

    Class<T> typeOfObject

    JsonHandler(Class<T> typeOfObject) {
        this.typeOfObject = typeOfObject
    }

    void handle(Context context) throws Exception {
        context.byMethod {
            it.post {

            }
            it.put {

            }
            it.delete {

            }
        }
    }

    abstract Operation add(T entity, PathTokens pathtokens)

    abstract Operation update(T entity, PathTokens pathtokens)

    abstract Operation delete(PathTokens pathtokens)
}
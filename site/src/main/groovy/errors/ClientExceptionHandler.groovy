package errors

import groovy.util.logging.Slf4j
import io.netty.handler.codec.http.HttpResponseStatus
import ratpack.error.ClientErrorHandler
import ratpack.handling.Context
import ratpack.http.Request

@Slf4j
class ClientExceptionHandler implements ClientErrorHandler {
    @Override
    void error(Context context, int statusCode) throws Exception {
        def request = context.request
        context.response.status(statusCode)

        HttpResponseStatus status = HttpResponseStatus.valueOf(statusCode)

        switch (status) {
            case HttpResponseStatus.METHOD_NOT_ALLOWED:
                methodNotAllowed(request)
                break
            case HttpResponseStatus.BAD_REQUEST:
                badRequest(request)
        }
    }

    void methodNotAllowed(Request request) {
        def headers = request.headers.asMultiValueMap()
        log.warn("Received a potentially malicious request with unexpected method to ${request.path} and the following headers: ${headers}")
    }

    void badRequest(Request request) {
        log.info("Recieved a potentially malicious bad request to ${request.path} and the following headers: ${request.headers.asMultiValueMap()}")
    }
}

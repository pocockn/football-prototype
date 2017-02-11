package handlers

import groovy.util.logging.Slf4j
import ratpack.handling.Context
import ratpack.handling.Handler
import ratpack.http.Request

@Slf4j
class TeamDetectionHandler implements Handler {
    void handle(Context context) {
        Request request = context.request
        log.info("$request")
        context.next()
    }
}

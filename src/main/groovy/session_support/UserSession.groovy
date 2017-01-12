package session_support

import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.exec.Operation
import ratpack.exec.Promise
import ratpack.session.Session
import service.user_service.UserStorageService

@Slf4j
class UserSession {
    private final Session session
    private final UserStorageService userStorageService
    private static final String UUID = "uuid"

    @Inject
    UserSession(Session session, UserStorageService userStorageService) {
        this.session = session
        this.userStorageService = userStorageService
    }


    Promise<Optional<?>> sessionValueFor() {
        session.data.map { it.get("UserId") }
    }


    Operation setUserIdSession(String id) {
        String userId = id
        log.info("Operation user method, setting the session with this data ${userId}")
        session.set("UserId", userId)
    }
}

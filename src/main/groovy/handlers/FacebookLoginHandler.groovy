package handlers

import groovy.util.logging.Slf4j
import org.pac4j.core.profile.UserProfile
import org.pac4j.oauth.client.FacebookClient
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import ratpack.pac4j.RatpackPac4j
import service.user_service.UserAccountService
import session_support.UserSession

import static user.OpenIdType.FACEBOOK

@Slf4j
class FacebookLoginHandler extends InjectionHandler {
    public void handle(Context ctx, UserAccountService userAccountService, UserSession userSession) {
        RatpackPac4j.login(ctx, FacebookClient).then { UserProfile facebookProfile ->
            facebookProfile.setRemembered(true)
            userAccountService.createUserObject(facebookProfile.id, FACEBOOK, facebookProfile.getAttribute("name").toString()).then { account ->
                if (account) {
                    userSession.setUserIdSession(facebookProfile.id).then {
                        userSession.sessionValueFor().then { id ->
                            log.info("id in session is  ${id.get()}")
                            ctx.redirect(302, "/auth")
                        }
                    }
                } else {
                    ctx.clientError(500)
                }
            }

        }
    }
}

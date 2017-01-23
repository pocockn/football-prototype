package handlers

import groovy.util.logging.Slf4j
import org.pac4j.core.profile.UserProfile
import org.pac4j.oauth.client.TwitterClient
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import ratpack.pac4j.RatpackPac4j
import service.user_service.UserAccountService
import session_support.UserSession

import static user.OpenIdType.TWITTER

@Slf4j
class TwitterLoginHandler extends InjectionHandler {
    public void handle(Context ctx, UserAccountService userAccountService, UserSession userSession) {
        RatpackPac4j.login(ctx, TwitterClient).then { UserProfile twitterProfile ->
            twitterProfile.setRemembered(true)
            userAccountService.createUserObject(twitterProfile.id, TWITTER, twitterProfile.getAttribute("name").toString()).then { account ->
                if (account) {
                    userSession.setUserIdSession(twitterProfile.id).then {
                        userSession.sessionValueFor().then { id ->
                            log.info("id in session is  ${id.get()}")
                            ctx.response.cookie("name", URLEncoder.encode(twitterProfile.getAttribute("name").toString()))
                            ctx.redirect(302, "/dashboard")
                        }
                    }
                } else {
                    ctx.clientError(500)
                }
            }

        }
    }
}
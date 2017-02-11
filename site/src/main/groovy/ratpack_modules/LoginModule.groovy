package ratpack_modules

import com.google.inject.AbstractModule
import handlers.FacebookLoginHandler
import handlers.TwitterLoginHandler

class LoginModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(TwitterLoginHandler)
        bind(FacebookLoginHandler)
    }
}

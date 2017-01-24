package ratpack_modules

import com.google.inject.AbstractModule
import service.user_service.UserAccountService
import service.user_service.UserStorageService
import service.user_service.UserStorageServiceImplementation
import session_support.UserSession

class UserAccountModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserAccountService)
        bind(UserStorageService).to(UserStorageServiceImplementation)
        bind(UserSession)
    }
}

package service.user_service

import groovy.util.logging.Slf4j
import ratpack.exec.Blocking
import ratpack.exec.Promise
import user.OpenIdType
import user.UserProfile

import javax.inject.Inject

@Slf4j
class UserAccountService {

    private final UserStorageServiceImplementation userStorageServiceImplementation

    @Inject
    UserAccountService(UserStorageServiceImplementation userStorageServiceImplementation) {
        this.userStorageServiceImplementation = userStorageServiceImplementation
    }

    Promise<UserProfile> createUserObject(String openId, OpenIdType openIdType, String name) {
        UserProfile userProfile = new UserProfile()
        userProfile.name = name
        userProfile.openIdType = openIdType
        userProfile.openId = openId
        log.info("Creating user object for ${name}, with open type of ${openIdType} and open ID of ${openId}")
        fetchOrCreateAccount(userProfile)

    }


    Promise<UserProfile> fetchOrCreateAccount(UserProfile userProfile) {
        if (userProfile) {
            userStorageServiceImplementation.fetch(userProfile.openId).flatMap { userAccount ->
                if (userAccount) {
                    Promise.value(userAccount)
                } else {
                    registerAccount(userProfile)
                }

            }
        } else {
            Promise.value(null)
        }
    }

    private Promise<UserProfile> registerAccount(UserProfile userProfile) {
        Blocking.get {
            new UserProfile(id: UUID.randomUUID(), openId: userProfile.openId, openIdType: userProfile.openIdType, name: userProfile.name)
        }.flatMap { userAccount ->
            log.info("Attemtping to save user ${userProfile.name}")
            userStorageServiceImplementation.saveUser(userAccount)
        }
    }
}

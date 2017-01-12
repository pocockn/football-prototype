package user
//ToDo: UserProfile represents our actual user data create another class called UserAccount which will handle authentication if an external service is not used

class UserProfile implements Serializable {
    String id
    String openId
    OpenIdType openIdType
    String name

}

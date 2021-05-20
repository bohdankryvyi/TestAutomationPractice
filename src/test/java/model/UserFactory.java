package model;

public class UserFactory {

    public static UserBuilder getStandartUser(String login, String password) {
        return new UserBuilder().withLogin(login).withPassword(password);
    }

    // we can add any other type of users and use it in our tests

}

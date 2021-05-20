package model;

public class UserBuilder {
    private User user;
    //constructor
    //we can set here some required values and then build just any additional(optional)
    public UserBuilder(){
        user = new User();
    }
//parameters
    public UserBuilder withLogin(String login){
        user.login = login;
        return this;
    }
    public UserBuilder withPassword(String password){
        user.password = password;
        return this;
    }

    public User build(){
        return user;
    }
}

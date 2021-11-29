package service;

import model.User;

public class UserCreator {
    public static final String USER_NAME = "testdata.user.name";
    public static final String USER_PASSWORD = "testdata.user.password";

    public static User withCredentialsFromProperty()
    {
        return new User(TestDataReader.getTestData(USER_NAME), TestDataReader.getTestData(USER_PASSWORD));
    }
}

package utils;

import model.User;
public class TestUtils {
    public static User createUser1() {
        return new User("jDoe", "hola", "John Doe", "01/01/2000");
    }

    public static User createUser2() {
        return new User("jane", "hoy", "Jane Doe", "05/05/1995");
    }
}

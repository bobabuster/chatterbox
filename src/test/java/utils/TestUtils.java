package utils;

import model.*;
public class TestUtils {
    public static User createUser1() {
        return new User("jDoe", "hola", "John Doe", "01/01/2000");
    }

    public static User createUser2() {
        return new User("jane", "hoy", "Jane Doe", "05/05/1995");
    }

    public static Post dummyPost() {
        return new Post("I <3 vanilla ice cream", createUser1());
    }
    public static Comment dummyComment() {
        return new Comment("Hello World", createUser2(), dummyPost());
    }
}

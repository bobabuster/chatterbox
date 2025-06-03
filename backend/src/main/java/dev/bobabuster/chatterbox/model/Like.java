package dev.bobabuster.chatterbox.model;

public class Like<T extends Likable> {
    private User user;
    private T target;

    public Like(User user, T target) {
        this.user = user;
        this.target = target;
    }

}

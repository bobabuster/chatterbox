package model;

public class Like<T extends Likable> {
    User user;
    T target;

    public Like(User user, T target) {
        this.user = user;
        this.target = target;
    }

}

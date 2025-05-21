public class Like<T extends Likable> {
    User user;
    T target;
    String timestamp;
    public Like(User user, T target, String timestamp) {
        this.user = user;
        this.target = target;
        this.timestamp = timestamp;
    }
}

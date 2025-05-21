public class Comment {
    String content;
    User commenter;
    User target;

    public Comment(String content, User commenter, User target) {
        this.content = content;
        this.commenter = commenter;
        this.target = target;
    }
}

package model;

public class Post implements Likable{
    private String content;
    private int numLikes;
    public Post(String content) {
        this.content = content;
        numLikes = 0;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public String getContent() {
        return content;
    }

    @Override
    public void addLike() {
        numLikes++;
    }

    @Override
    public void removeLike() {
        if (numLikes == 0) {
            throw new IllegalStateException("Can't remove a like from a comment with no likes");
        }
    }
}

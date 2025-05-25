package model;

import java.util.ArrayList;
import java.util.List;

public class Comment implements Likable {
    private String content;
    private User author;
    private User target;
    private boolean isDeleted;
    private int numLikes;
    private List<Like> likeList;


    public Comment(String content, User author, User target) {
        this.content = content;
        this.author = author;
        this.target = target;
        isDeleted = false;
        numLikes = 0;
        likeList = new ArrayList<>();
    }

    public String getContent() {
        assertNotDeleted();
        return content;
    }
    public int getNumLikes() {
        assertNotDeleted();
        return numLikes;
    }

    public List<Like> getLikeList() {
        assertNotDeleted();
        return likeList;
    }

    public void edit(String newContent) {
        assertNotDeleted();
        content = newContent;
    }


    public boolean isDeleted() {
        return isDeleted();
    }

    @Override
    public void addLike() {
        assertNotDeleted();
        numLikes++;
    }

    @Override
    public void removeLike() {
       assertNotDeleted();
        if (numLikes == 0) {
            throw new IllegalStateException("Can't remove a like from a comment with no likes");
        }
        numLikes--;
    }

    public void assertNotDeleted() {
        if (isDeleted()) {
            throw new IllegalStateException("This comment is already deleted!");
        }
    }
}

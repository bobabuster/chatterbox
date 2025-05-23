package model;

import java.util.ArrayList;
import java.util.List;

public class Comment implements Likable {
    String content;
    User author;
    User target;
    boolean isDeleted;
    int numLikes;
    List<Like> likeList;


    public Comment(String content, User author, User target) {
        this.content = content;
        this.author = author;
        this.target = target;
        isDeleted = false;
        numLikes = 0;
        likeList = new ArrayList<>();
    }

    public String getContent() {
        return content;
    }
    public int getNumLikes() {
        return numLikes;
    }

    public List<Like> getLikeList() {
        return likeList;
    }

    public void edit(String newContent) {
        if (isDeleted) {
            content = newContent;
        }
    }

    public void delete() {
        isDeleted = true;
        content = "[deleted]";
    }

    public boolean isDeleted() {
        return isDeleted();
    }

    @Override
    public void addLike() {
        if (isDeleted) {
            throw new IllegalStateException("This comment is already deleted!");
        }
        numLikes++;
    }

    @Override
    public void removeLike() {
        if (isDeleted) {
            throw new IllegalStateException("This comment is already deleted!");
        }
        if (numLikes == 0) {
            throw new IllegalStateException("Can't remove a like from a comment with no likes");
        }
        numLikes--;

    }
}

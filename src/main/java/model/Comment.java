package model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Comment implements Likable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment id
    private long id;

    private String content;
    private User author;
    private Post target;
    private boolean isDeleted;
    private int numLikes;
    private List<Like> likeList;


    public Comment(String content, User author, Post target) {
        this.content = content;
        this.author = author;
        this.target = target;
        isDeleted = false;
        numLikes = 0;
        likeList = new ArrayList<>();
    }

    public User getAuthor() {
        return author;
    }

    public Post getTarget() {
        return target;
    }

    public String getContent() {
        assertNotDeleted();
        return content;
    }
    public int getNumLikes() {
        assertNotDeleted();
        return numLikes;
    }

    public long getId() {
        return id;
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
        return isDeleted;
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

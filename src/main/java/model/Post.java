package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Post implements Likable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment id
    private long id;
    private String content;
    private int numLikes;
    private User author;
    public Post(String content, User author) {
        this.content = content;
        numLikes = 0;
        this.author = author;
    }

    public void edit(String newContent) {
        content = newContent;
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

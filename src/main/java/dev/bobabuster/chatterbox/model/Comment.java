package dev.bobabuster.chatterbox.model;

import jakarta.persistence.*;

@Entity
public class Comment implements Likable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment id
    private long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "post_id")  // foreign key column in Comment table
    private Post target;
    private boolean isDeleted;
    private int numLikes;



    public Comment(String content, User author, Post target) {
        this.content = content;
        this.author = author;
        this.target = target;
        isDeleted = false;
        numLikes = 0;
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

    public void setId(long id) {
        this.id = id;
    }
}

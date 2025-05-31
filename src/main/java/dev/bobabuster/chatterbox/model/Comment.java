package dev.bobabuster.chatterbox.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Comment implements Likable, Identifiable<Long> {

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

    protected Comment() {}

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

    public Long getId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) {
            return false;
        }

        Comment other = (Comment) o;
        return id == other.id;
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

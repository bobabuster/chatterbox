package dev.bobabuster.chatterbox.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Post implements Likable, Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment id
    private long id;
    private String content;
    private int numLikes;


    @ManyToOne
    @JoinColumn(name = "user_id")  // the foreign key column in the Post table
    private User author;

    protected Post() {}
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
    public User getAuthor() { return author;
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
        numLikes--;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) {
            return false;
        }

        Post other = (Post) o;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

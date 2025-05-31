package dev.bobabuster.chatterbox.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Friendship implements Identifiable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment id
    private long id;

    @ManyToOne
    @JoinColumn(name = "user1_id")
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user2_id")
    private User user2;
    private Status status;

    public Friendship(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
        status = Status.PENDING;
    }

    public Set<User> getUsers() {
        return Set.of(user1, user2);
    }

    public Status getStatus() {
        return status;
    }

    public void acceptRequest() {
        assertPending();
        status = Status.ACCEPTED;
    }

    public void rejectRequest() {
        assertPending();
        status = Status.REJECTED;
    }

    public void removeFriendship() {
        assertAccepted();
        status = Status.REMOVED;
    }



    public void assertPending() {
        if (status != Status.PENDING) {
            throw new IllegalStateException("This friendship is not pending");
        }
    }

    public void assertAccepted() {
        if (status != Status.ACCEPTED) {
            throw new IllegalStateException("This friendship is not pending");
        }
    }

    @Override
    public Long getId() {
        return id;
    }
}

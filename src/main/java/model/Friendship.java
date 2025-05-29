package model;

import java.util.Set;

enum Status {
    PENDING,
    ACCEPTED,
    REJECTED,
}
public class Friendship {
    private User user1;
    private User user2;
    private Status status;

    public void Friendship(User user1, User user2) {
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



    public void assertPending() {
        if (status != Status.PENDING) {
            throw new IllegalStateException("This friendship is not pending");
        }
    }

}

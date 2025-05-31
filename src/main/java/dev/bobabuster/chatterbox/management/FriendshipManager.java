package dev.bobabuster.chatterbox.management;

import dev.bobabuster.chatterbox.model.Friendship;
import jakarta.persistence.EntityNotFoundException;
import dev.bobabuster.chatterbox.model.User;
import dev.bobabuster.chatterbox.model.Status;

import org.springframework.stereotype.Service;
import dev.bobabuster.chatterbox.repository.FriendshipRepository;

import java.util.Optional;

@Service
public class FriendshipManager {
    private final FriendshipRepository repo;

    public FriendshipManager(FriendshipRepository repo) {
        this.repo = repo;
    }

    // TODO: make sure we don't create duplicate friendships
    public Friendship createFriendship(User u1, User u2) {
        Friendship f = new Friendship(u1, u2);
        repo.save(f);
        return f;
    }

    public Friendship getFriendship(long id) {
        Optional<Friendship> opt = repo.findById(id);
        if (!opt.isPresent()) {
            throw new EntityNotFoundException("Comment with id " + id + " not found");
        } else {
            return opt.get();
        }
    }

    public void updateRequest(long id, Status status) {
        Friendship f = getFriendship(id);
        f.assertPending();

        switch (status) {
            case ACCEPTED:
                f.acceptRequest();
            case REJECTED:
                f.rejectRequest();
            default:
                throw new IllegalArgumentException("Invalid status update: " + status);
        }
    }

    public void removeFriend(long id) {
        Friendship f = getFriendship(id);
        f.assertAccepted();
        f.removeFriendship();
    }


}

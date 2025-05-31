package dev.bobabuster.chatterbox.management;

import dev.bobabuster.chatterbox.model.Friendship;
import dev.bobabuster.chatterbox.repository.UserRepository;
import dev.bobabuster.chatterbox.utils.EntityUtils;
import jakarta.persistence.EntityNotFoundException;
import dev.bobabuster.chatterbox.model.User;
import dev.bobabuster.chatterbox.model.Status;

import org.springframework.stereotype.Service;
import dev.bobabuster.chatterbox.repository.FriendshipRepository;

import java.util.Optional;

@Service
public class FriendshipManager {
    private final FriendshipRepository friendRepo;
    private final UserRepository userRepo;

    public FriendshipManager(FriendshipRepository friendRepo, UserRepository userRepo) {
        this.friendRepo = friendRepo;
        this.userRepo = userRepo;
    }

    // TODO: make sure we don't create duplicate friendships
    // TODO: friendship can only be created if they are not currently friends (rejected, pending ok)
    public Friendship createFriendship(User u1, User u2) {
        if (u1.equals(u2)) {
            throw new IllegalArgumentException("Users must be distinct");
        }
        EntityUtils.saveOrGetExisting(u1, userRepo);
        EntityUtils.saveOrGetExisting(u2, userRepo);
        Friendship f = new Friendship(u1, u2);
        friendRepo.save(f);
        return f;
    }

    public Friendship getFriendship(long id) {
        Optional<Friendship> opt = friendRepo.findById(id);
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
                break;
            case REJECTED:
                f.rejectRequest();
                break;
            default:
                throw new IllegalArgumentException("Invalid status update: " + status);
        }
    }

    public void removeFriend(long id) {
        if (!friendRepo.existsById(id)) {
            throw new EntityNotFoundException("friendship with id " + id + " not found");
        }
        Friendship f = getFriendship(id);
        f.assertAccepted();
        f.removeFriendship();
        friendRepo.deleteById(id);
    }


}

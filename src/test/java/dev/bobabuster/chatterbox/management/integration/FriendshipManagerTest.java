package dev.bobabuster.chatterbox.management.integration;

import dev.bobabuster.chatterbox.management.FriendshipManager;
import dev.bobabuster.chatterbox.model.*;
import dev.bobabuster.chatterbox.repository.FriendshipRepository;
import dev.bobabuster.chatterbox.repository.UserRepository;
import dev.bobabuster.chatterbox.utils.EntityUtils;
import dev.bobabuster.chatterbox.utils.TestUtils;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.parser.Entity;

import static com.google.common.truth.Truth.assertThat;

@SpringBootTest
@Transactional
public class FriendshipManagerTest {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private FriendshipRepository friendRepo;

    @Autowired
    private FriendshipManager manager;


    @Test
    public void basic() {
        User u1 = TestUtils.createUser1();
        User u2 = TestUtils.createUser2();
        EntityUtils.saveOrGetExisting(u1, userRepo);
        EntityUtils.saveOrGetExisting(u2, userRepo);

        Friendship f1 = manager.createFriendship(u1, u2);
        assertThat(f1.getUsers()).containsExactly(u1, u2);

        Friendship f2 = manager.getFriendship(f1.getId());
        assertThat(f2.getUsers()).containsExactly(u1, u2);

        manager.updateRequest(f2.getId(), Status.ACCEPTED);
        Friendship f3 = manager.getFriendship(f2.getId());
        assertThat(f3.getStatus()).isEqualTo(Status.ACCEPTED);

        manager.removeFriend(f3.getId());
        assertThat(friendRepo.existsById(f3.getId())).isFalse();

    }

}

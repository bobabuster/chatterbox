package dev.bobabuster.chatterbox.management.integration;

import dev.bobabuster.chatterbox.management.*;
import dev.bobabuster.chatterbox.model.*;
import dev.bobabuster.chatterbox.repository.PostRepository;
import dev.bobabuster.chatterbox.repository.UserRepository;
import dev.bobabuster.chatterbox.utils.TestUtils;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.google.common.truth.Truth.assertThat;

@SpringBootTest
@Transactional
public class PostManagerTest {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PostManager manager;

    @Test
    public void basic() {
        User u = TestUtils.createUser1();
        Post p1 = manager.createPost("I like Pokemon", u);
        assertThat(p1.getAuthor().equals(u)).isTrue();
        assertThat(p1.getContent()).isEqualTo("I like Pokemon");

        Post p2 = manager.getPost(p1.getId());
        assertThat(p2.getAuthor().equals(u)).isTrue();
        assertThat(p2.getContent()).isEqualTo("I like Pokemon");

        manager.updatePost(p2.getId(), "I now don't like Pokemon");
        Post p3 = manager.getPost(p2.getId());

        manager.deletePost(p3.getId());
        assertThat(postRepo.existsById(p3.getId())).isFalse();

    }

}

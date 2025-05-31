package dev.bobabuster.chatterbox.management.integration;

import dev.bobabuster.chatterbox.management.CommentManager;
import dev.bobabuster.chatterbox.model.Comment;
import dev.bobabuster.chatterbox.model.Post;
import dev.bobabuster.chatterbox.model.User;
import dev.bobabuster.chatterbox.repository.PostRepository;
import dev.bobabuster.chatterbox.repository.UserRepository;
import dev.bobabuster.chatterbox.utils.TestUtils;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dev.bobabuster.chatterbox.repository.CommentRepository;
import static com.google.common.truth.Truth.assertThat;

@SpringBootTest
public class CommentManagerTest {
    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PostRepository postRepo;

    private CommentManager manager;

    @BeforeEach
    @Transactional
    public void setUp() {
        commentRepo.deleteAll();
        postRepo.deleteAll();
        userRepo.deleteAll();

        manager = new CommentManager(commentRepo, userRepo, postRepo);
    }

    @Test
    public void basic() {
        User u = TestUtils.createUser2();
        Post p = new Post("Comment hello", TestUtils.createUser1());
        Comment c = manager.createComment("hello", u, p);

        assertThat(c.getTarget()).isEqualTo(p);
        assertThat(c.getContent()).isEqualTo("hello");
        assertThat(c.getAuthor()).isEqualTo(u);

        Comment c2 = manager.getComment(c.getId());
        assertThat(c2.getContent()).isEqualTo("hello");
        assertThat(c2.getAuthor().equals(u)).isTrue();
        assertThat(c2.getTarget().equals(p)).isTrue();

        manager.updateComment(c2.getId(), "I am updated!");
        Comment c3 = manager.getComment(c2.getId());
        assertThat(c3.getContent()).isEqualTo("I am updated!");
        assertThat(c3.getAuthor().equals(u)).isTrue();
        assertThat(c3.getTarget().equals(p)).isTrue();

        manager.deleteComment(c3.getId());
        assertThat(commentRepo.existsById(c3.getId())).isFalse();
    }


}

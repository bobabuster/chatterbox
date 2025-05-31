package dev.bobabuster.chatterbox.management.integration;

import dev.bobabuster.chatterbox.management.CommentManager;
import dev.bobabuster.chatterbox.model.Comment;
import dev.bobabuster.chatterbox.model.Post;
import dev.bobabuster.chatterbox.model.User;
import dev.bobabuster.chatterbox.repository.PostRepository;
import dev.bobabuster.chatterbox.repository.UserRepository;
import dev.bobabuster.chatterbox.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dev.bobabuster.chatterbox.repository.CommentRepository;
import static com.google.common.truth.Truth.assertThat;

@SpringBootTest(classes = dev.bobabuster.chatterbox.Main.class)
public class CommentManagerTest {
    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PostRepository postRepo;

    private CommentManager manager;

    @BeforeEach
    public void setUp() {
        manager = new CommentManager(commentRepo, userRepo, postRepo);
    }



    @Test
    public void basicSuite() {
        User u = TestUtils.createUser2();
        Post p = new Post("Comment hello", TestUtils.createUser1());
        Comment c = manager.createComment("hello", u, p);

        assertThat(c.getTarget()).isEqualTo(p);
        assertThat(c.getContent()).isEqualTo("hello");
        assertThat(c.getAuthor()).isEqualTo(u);
    }
}

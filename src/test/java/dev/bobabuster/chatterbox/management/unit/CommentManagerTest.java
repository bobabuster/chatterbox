package dev.bobabuster.chatterbox.management.unit;

import dev.bobabuster.chatterbox.management.CommentManager;
import dev.bobabuster.chatterbox.model.Comment;
import dev.bobabuster.chatterbox.model.Post;
import dev.bobabuster.chatterbox.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import dev.bobabuster.chatterbox.repository.CommentRepository;
import dev.bobabuster.chatterbox.utils.TestUtils;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentManagerTest {
    @Mock
    private CommentRepository repo;

    @InjectMocks
    private CommentManager manager;

    @Test
    public void basicSuite() {
        User u = TestUtils.createUser2();
        Post p = new Post("Comment hello", TestUtils.createUser1());
        Comment c = new Comment("hello", u, p);
        c.setId(1L);

        when(repo.save(any(Comment.class))).thenReturn(c);
        Comment savedComment = manager.createComment("hello", u, p);

        assertThat(savedComment.getTarget()).isEqualTo(p);
        assertThat(savedComment.getContent()).isEqualTo("hello");
        assertThat(savedComment.getAuthor()).isEqualTo(u);
        assertThat(savedComment.getId()).isEqualTo(1L);

        when(repo.findById(1L)).thenReturn(Optional.of(c));
        assertThat(manager.getComment(1L).getContent()).isEqualTo("hello");

        assertThat(manager.updateComment(1L, "new hello").getContent()).isEqualTo("new hello");

        when(repo.existsById(1L)).thenReturn(true);
        manager.deleteComment(1L);
        verify(repo, times(1)).deleteById(1L);



    }
}

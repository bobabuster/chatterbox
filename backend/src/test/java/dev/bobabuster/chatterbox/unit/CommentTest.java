package dev.bobabuster.chatterbox.unit;

import dev.bobabuster.chatterbox.model.Comment;
import dev.bobabuster.chatterbox.model.Post;
import dev.bobabuster.chatterbox.model.User;
import dev.bobabuster.chatterbox.utils.TestUtils;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommentTest {
    @Test
    public void getterTest() {
        User user1 = TestUtils.createUser1();
        Post post = new Post("Hola", user1);
        Comment comment = new Comment("I love cookies", user1, post);

        assertThat(comment.getContent()).isEqualTo("I love cookies");
        assertThat(comment.getNumLikes()).isEqualTo(0);
    }




}

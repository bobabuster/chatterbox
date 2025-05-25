package unit;

import model.User;
import org.junit.jupiter.api.Test;
import utils.TestUtils;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommentTest {
    @Test
    public void getterTest() {
        User user1 = TestUtils.createUser1();
        User user2 = TestUtils.createUser2();
        model.Comment comment = new model.Comment("I love cookies", user1, user2);

        assertThat(comment.getContent()).isEqualTo("I love cookies");
        assertThat(comment.getNumLikes()).isEqualTo(0);
        assertThat(comment.getLikeList()).isEmpty();
    }




}

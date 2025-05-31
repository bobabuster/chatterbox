package dev.bobabuster.chatterbox.unit;

import static com.google.common.truth.Truth.assertThat;

import dev.bobabuster.chatterbox.model.User;
import org.junit.jupiter.api.Test;
import dev.bobabuster.chatterbox.utils.TestUtils;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {

    @Test
    public void userGetterTests() {
        User user = new User("jDoe", "hola", "John Doe", "01/01/2000");
        assertThat(user.getUsername()).isEqualTo("jDoe");
        assertThat(user.getPassword()).isEqualTo("hola");
        assertThat(user.getRealName()).isEqualTo("John Doe");
        assertThat(user.getdob()).isEqualTo("01/01/2000");
    }

    @Test
    public void getAndChangeDate() {
        User user = new User("jDoe", "hola", "John Doe", "01/01/2000");
        assertThat(user.getdob()).isEqualTo("01/01/2000");

        user.changedob("05/05/1995");
        assertThat(user.getdob()).isEqualTo("05/05/1995");
    }

    @Test
    public void changeUserInfo() {
        User user = new User("jDoe", "hola", "John Doe", "01/01/2000");
        user.changePassword("hoy");
        assertThat(user.getPassword()).isEqualTo("hoy");

        user.changeUsername("jane");
        assertThat(user.getUsername()).isEqualTo(("jane"));

        user.changeRealName("Jane Doe");
        assertThat(user.getRealName()).isEqualTo("Jane Doe");
    }

    @Test
    public void badDate() {
        User user = TestUtils.createUser1();
        assertThrows(IllegalArgumentException.class, () -> {
            new User("dummy", "dummy", "dummy", "bad_date");

        });
        assertThrows(IllegalArgumentException.class, () -> {
            user.changedob("another_bad_date");
        });
    }


}

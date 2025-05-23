package unit;

import com.google.common.truth.Truth;
import model.User;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
public class UserTest {

    @Test
    public void userGetterTests() {
        User user = new User("jDoe", "hola", "John Doe", "01/01/2000");
        Truth.assertThat(user.getUsername()).isEqualTo("jDoe");
        Truth.assertThat(user.getPassword()).isEqualTo("hola");
        Truth.assertThat(user.getRealName()).isEqualTo("John Doe");
        Truth.assertThat(user.getdob()).isEqualTo("01/01/2000");
    }

    @Test
    public void getAndChangeDate() {
        User user = new User("jDoe", "hola", "John Doe", "01/01/2000");
        Truth.assertThat(user.getdob()).isEqualTo("01/01/2000");

        user.changedob("05/05/1995");
        Truth.assertThat(user.getdob()).isEqualTo("05/05/1995");
    }

    @Test
    public void changeUserInfo() {
        User user = new User("jDoe", "hola", "John Doe", "01/01/2000");
        user.changePassword("hoy");
        Truth.assertThat(user.getPassword()).isEqualTo("hoy");

        user.changeUsername("jane");
        Truth.assertThat(user.getUsername()).isEqualTo(("jane"));

        user.changeRealName("Jane Doe");
        Truth.assertThat(user.getRealName()).isEqualTo("Jane Doe");
    }
}

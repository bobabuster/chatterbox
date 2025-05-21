import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
public class BasicUserTest {

    @Test
    public void userGetterTests() {
        User user = new User("jDoe", "hola", "John Doe", "01/01/2000");
        assertThat(user.getUsername()).isEqualTo("jDoe");
        assertThat(user.getRealName()).isEqualTo("John Doe");
        assertThat(user.getdob()).isEqualTo("01/01/2000");
    }
}

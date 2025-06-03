package dev.bobabuster.chatterbox.management.integration;

import dev.bobabuster.chatterbox.management.UserManager;
import dev.bobabuster.chatterbox.model.User;
import dev.bobabuster.chatterbox.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.google.common.truth.Truth.assertThat;

@SpringBootTest
public class UserManagerTest {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserManager manager;

    @Test
    public void basic() {
        User u = manager.createUser("bob", "iambob", "bob doe", "01/01/2000");
        assertThat(u.getdob()).isEqualTo("01/01/2000");
        assertThat(u.getPassword()).isEqualTo("iambob");
        assertThat(u.getRealName()).isEqualTo("bob doe");
        assertThat(u.getUsername()).isEqualTo("bob");

        User u2 = manager.getUser(u.getId());
        assertThat(u2.getdob()).isEqualTo("01/01/2000");
        assertThat(u2.getPassword()).isEqualTo("iambob");
        assertThat(u2.getRealName()).isEqualTo("bob doe");
        assertThat(u2.getUsername()).isEqualTo("bob");

        manager.updateDob(u2.getId(), "01/01/1999");
        User u3 = manager.getUser(u2.getId());
        assertThat(u3.getdob()).isEqualTo("01/01/1999");

        manager.updatePassword(u3.getId(), "iamnotbob");
        User u4 = manager.getUser(u3.getId());
        assertThat(u4.getPassword()).isEqualTo("iamnotbob");

        

    }



}

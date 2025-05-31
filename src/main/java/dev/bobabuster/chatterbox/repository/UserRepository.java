package dev.bobabuster.chatterbox.repository;

import dev.bobabuster.chatterbox.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

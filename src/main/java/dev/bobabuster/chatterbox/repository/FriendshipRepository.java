package dev.bobabuster.chatterbox.repository;

import dev.bobabuster.chatterbox.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
}

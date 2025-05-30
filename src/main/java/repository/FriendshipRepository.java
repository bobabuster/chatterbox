package repository;

import model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
}

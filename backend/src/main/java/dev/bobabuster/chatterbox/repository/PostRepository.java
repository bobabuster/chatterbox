package dev.bobabuster.chatterbox.repository;

import dev.bobabuster.chatterbox.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}

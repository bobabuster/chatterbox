package dev.bobabuster.chatterbox.management;

import dev.bobabuster.chatterbox.model.Post;
import dev.bobabuster.chatterbox.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import dev.bobabuster.chatterbox.repository.PostRepository;

import java.util.Optional;

@Service
public class PostManager {
    private final PostRepository repo;

    public PostManager(PostRepository repo) {
        this.repo = repo;
    }

    public Post createPost(String content, User author) {
        Post post = new Post(content, author);
        return repo.save(post);
    }

    public Post getPost(long id) {
        Optional<Post> opt = repo.findById(id);
        if (!opt.isPresent()) {
            throw new EntityNotFoundException("Post with id " + id + " not found");
        } else {
            return opt.get();
        }
    }

    public void updatePost(long id, String newContent) {
        Post p = getPost(id);
        p.edit(newContent);
        repo.save(p);
    }


    public void deletePost(long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Post with id " + id + " not found");
        } else {
            repo.deleteById(id);
        }
    }
}

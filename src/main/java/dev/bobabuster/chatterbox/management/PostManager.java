package dev.bobabuster.chatterbox.management;

import dev.bobabuster.chatterbox.model.Post;
import dev.bobabuster.chatterbox.model.User;
import dev.bobabuster.chatterbox.repository.UserRepository;
import dev.bobabuster.chatterbox.utils.EntityUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import dev.bobabuster.chatterbox.repository.PostRepository;

import java.util.Optional;

@Service
public class PostManager {
    private final PostRepository postRepo;
    private final UserRepository userRepo;

    public PostManager(PostRepository postRepo, UserRepository userRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    public Post createPost(String content, User author) {
        EntityUtils.saveOrGetExisting(author, userRepo);
        Post post = new Post(content, author);
        return postRepo.save(post);
    }

    public Post getPost(long id) {
        Optional<Post> opt = postRepo.findById(id);
        if (!opt.isPresent()) {
            throw new EntityNotFoundException("Post with id " + id + " not found");
        } else {
            return opt.get();
        }
    }

    public void updatePost(long id, String newContent) {
        Post p = getPost(id);
        p.edit(newContent);
        postRepo.save(p);
    }


    public void deletePost(long id) {
        if (!postRepo.existsById(id)) {
            throw new EntityNotFoundException("Post with id " + id + " not found");
        } else {
            postRepo.deleteById(id);
        }
    }
}

package dev.bobabuster.chatterbox.management;

import dev.bobabuster.chatterbox.model.Comment;
import dev.bobabuster.chatterbox.model.Post;
import dev.bobabuster.chatterbox.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import dev.bobabuster.chatterbox.repository.CommentRepository;

import java.util.*;

@Service
public class CommentManager {
    private final CommentRepository repo;


    public CommentManager(CommentRepository repo) {
        this.repo = repo;
    }


    public Comment createComment(String content, User user, Post target) {
        Comment comment = new Comment(content, user, target);
        return repo.save(comment);
    }

    public Comment getComment(long id) {
        Optional<Comment> opt = repo.findById(id);
        if (!opt.isPresent()) {
            throw new EntityNotFoundException("Comment with id " + id + " not found");
        } else {
            return opt.get();
        }
    }

    public Comment updateComment(long id, String newContent) {
        Comment c = getComment(id);
        c.edit(newContent);
        return repo.save(c);
    }


    public void deleteComment(long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Comment with id " + id + " not found");
        } else {
            repo.deleteById(id);
        }
    }


}

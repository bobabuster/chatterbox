package management;

import jakarta.persistence.EntityNotFoundException;
import model.*;
import org.springframework.stereotype.Service;
import repository.CommentRepository;

import java.util.*;
import java.util.List;

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

    public void updateComment(long id, String newContent) {
        Comment c = getComment(id);
        c.edit(newContent);
        repo.save(c);
    }


    public void deleteComment(long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Comment with id " + id + " not found");
        } else {
            repo.deleteById(id);
        }
    }


}

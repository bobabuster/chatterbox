package management;

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

    public Comment getComment(String content, User user, Post target) {
        return null;
    }



    public Comment deleteComment() {
        return null;
    }


}

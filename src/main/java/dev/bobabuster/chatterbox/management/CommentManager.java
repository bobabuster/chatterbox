package dev.bobabuster.chatterbox.management;

import dev.bobabuster.chatterbox.model.Comment;
import dev.bobabuster.chatterbox.model.Post;
import dev.bobabuster.chatterbox.model.User;
import dev.bobabuster.chatterbox.repository.PostRepository;
import dev.bobabuster.chatterbox.repository.UserRepository;
import dev.bobabuster.chatterbox.utils.EntityUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.bobabuster.chatterbox.repository.CommentRepository;

import java.util.*;

@Service
public class CommentManager {


    private final CommentRepository commentRepo;
    private final UserRepository userRepo;
    private final PostRepository postRepo;

    @Autowired
    public CommentManager(CommentRepository cRepo, UserRepository uRepo, PostRepository pRepo) {
        commentRepo = cRepo;
        userRepo = uRepo;
        postRepo = pRepo;
    }


    public Comment createComment(String content, User user, Post target) {
        EntityUtils.saveOrGetExisting(user, userRepo);
        EntityUtils.saveOrGetExisting(target.getAuthor(), userRepo);
        EntityUtils.saveOrGetExisting(target, postRepo);
        Comment comment = new Comment(content, user, target);
        return commentRepo.save(comment);
    }

    public Comment getComment(long id) {
        Optional<Comment> opt = commentRepo.findById(id);
        if (!opt.isPresent()) {
            throw new EntityNotFoundException("Comment with id " + id + " not found");
        } else {
            return opt.get();
        }
    }

    @Transactional
    public Comment updateComment(long id, String newContent) {
        Comment c = getComment(id);
        c.edit(newContent);
        return commentRepo.save(c);
    }


    public void deleteComment(long id) {
        if (!commentRepo.existsById(id)) {
            throw new EntityNotFoundException("Comment with id " + id + " not found");
        } else {
            commentRepo.deleteById(id);
        }
    }


}

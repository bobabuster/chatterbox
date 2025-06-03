package dev.bobabuster.chatterbox.management;

import dev.bobabuster.chatterbox.exceptions.UsernameAlreadyExistsException;
import dev.bobabuster.chatterbox.model.*;
import dev.bobabuster.chatterbox.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserManager {

    private final UserRepository userRepo;

    @Autowired
    public UserManager(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User createUser(String username, String password) {
        ensureUniqueUsername(username);
        User u = new User(username, password);
        return userRepo.save(u);
    }

    public User createUser(String username, String password, String realName, String dob) {
        ensureUniqueUsername(username);
        User u = new User(username, password, realName, dob);
        return userRepo.save(u);
    }

    public User getUser(Long userId) {
        Optional<User> opt = userRepo.findById(userId);
        if (!opt.isPresent()) {
            throw new EntityNotFoundException("User with id " + userId + " not found");
        } else {
            return opt.get();
        }
    }

    public User updatePassword(Long id, String newPassword) {
        User u = getUser(id);
        u.changePassword(newPassword);
        return userRepo.save(u);
    }

    public User updateDob(Long id, String dob) {
        User u = getUser(id);
        u.changedob(dob);
        return userRepo.save(u);
    }

    public User updateRealName(Long id, String newRealName) {
        User u = getUser(id);
        u.changeRealName(newRealName);
        return userRepo.save(u);
    }

    public void deleteUser(long id) {
        if (!userRepo.existsById(id)) {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
        userRepo.deleteById(id);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public void ensureUniqueUsername(String username) {
        if (userRepo.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException("User with that username already exists!");
        }
    }

}

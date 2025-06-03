package dev.bobabuster.chatterbox.controller;

import dev.bobabuster.chatterbox.dto.UpdateDobRequest;
import dev.bobabuster.chatterbox.dto.UpdatePasswordRequest;
import dev.bobabuster.chatterbox.dto.UserRequest;
import dev.bobabuster.chatterbox.dto.UpdateNameRequest;
import dev.bobabuster.chatterbox.management.UserManager;
import dev.bobabuster.chatterbox.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserManager manager;

    @Autowired
    public UserController(UserManager manager) {
        this.manager = manager;
    }

    @GetMapping("/users")
    public List<User> all() {
        return manager.getAllUsers();
    }

    @PostMapping("/users")
    public User newUser(@RequestBody UserRequest req) {
        return manager.createUser(req.getUsername(), req.getPassword());
    }

    @PatchMapping("/users/{id}/password")
    public void updatePassword(@PathVariable("id") Long id, @RequestBody UpdatePasswordRequest updatePasswordRequest) {
        manager.updatePassword(id, updatePasswordRequest.getNewPassword());
    }

    @PatchMapping("/users/{id}/name")
    public void updateRealName(@PathVariable("id") Long id, @RequestBody UpdateNameRequest req) {
        manager.updateRealName(id, req.getName());
    }

    @PatchMapping("/users/{id}/dob")
    public void updateDob(@PathVariable("id") Long id, @RequestBody UpdateDobRequest req) {
        manager.updateDob(id, req.getDob());
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        manager.deleteUser(id);
    }


}

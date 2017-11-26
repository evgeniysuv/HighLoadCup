package com.esuvorov.controller;

import com.esuvorov.model.User;
import com.esuvorov.service.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable long id) {
        return userService.getUser(id);
    }

    @PostMapping("/{id}")
    public User updateUser(@RequestBody JSONObject user, @PathVariable long id) {
        return userService.updateUser(id, user);
    }

    @PostMapping("/new")
    public User createUser(@RequestBody JSONObject user) {
        return userService.createUser(user);
    }
}

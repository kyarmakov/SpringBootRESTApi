package com.yarmakov.SpringBootRESTApi.controllers;

import com.yarmakov.SpringBootRESTApi.dto.UserRequest;
import com.yarmakov.SpringBootRESTApi.entities.User;
import com.yarmakov.SpringBootRESTApi.exceptions.UserNotFoundException;
import com.yarmakov.SpringBootRESTApi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.saveUser(userRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.findUserById(id));
    }
}

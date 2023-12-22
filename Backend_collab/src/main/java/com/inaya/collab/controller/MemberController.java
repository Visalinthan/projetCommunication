package com.inaya.collab.controller;

import com.inaya.collab.model.User;
import com.inaya.collab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get all members
    @GetMapping(path = "/all")
    public ResponseEntity<List<User>> getAllMembers() {
        List<User> appointments = userService.getAllMembers();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
}

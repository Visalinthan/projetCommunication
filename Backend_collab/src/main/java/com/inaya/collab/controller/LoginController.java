package com.inaya.collab.controller;

import com.inaya.collab.exceptions.LoginException;
import com.inaya.collab.dto.LoginDto;
import com.inaya.collab.model.User;
import com.inaya.collab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/login")
public class LoginController {


    @Autowired
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> loginUser(@RequestBody LoginDto userDto){

        try {
            Optional<User> user = userService.getByUsername(userDto.getUsername());

            if (user == null || !user.get().getPassword().equals(userDto.getPassword())) {
                throw new LoginException("Invalid credentials");
            }

            return ResponseEntity.ok(user);
        } catch (LoginException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }

}

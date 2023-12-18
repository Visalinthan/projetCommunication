package com.inaya.collab.controller;

import com.inaya.collab.controller.exceptions.AddException;
import com.inaya.collab.dto.LoginDto;
import com.inaya.collab.model.User;
import com.inaya.collab.repository.UserRepository;
import com.inaya.collab.security.AuthenticationBean;
import com.inaya.collab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class LoginController {

    UserRepository userRepo;

    @Autowired
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }


    // Get all members
    @GetMapping(path = "/all")
    public ResponseEntity<List<User>> getAllMembers() {
        List<User> appointments = userService.getAllMembers();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }


   /* @GetMapping(path = "/basicauth")
    public AuthenticationBean basicauth() {
        return new AuthenticationBean("You are authenticated");
    }*/

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto userDto){
        AddException msg = new AddException("Identifiant or password incorrect");

        User user = userRepo.findByUsername(userDto.getUsername()).get();
        if(user.getPassword().equals(userDto.getPassword())){
            return ResponseEntity.ok(user);
        }

        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg.getMessage());
        if (user.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg.getMessage());
        }

    }

}

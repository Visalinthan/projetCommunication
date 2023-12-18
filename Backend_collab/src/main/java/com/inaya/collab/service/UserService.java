package com.inaya.collab.service;

import com.inaya.collab.model.User;
import com.inaya.collab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // Get a list of all members
    public List<User> getAllMembers() {
        return this.userRepo.findAll();
    }
}

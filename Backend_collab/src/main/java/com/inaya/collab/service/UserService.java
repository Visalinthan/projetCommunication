package com.inaya.collab.service;

import com.inaya.collab.exceptions.EntityNotFoundException;
import com.inaya.collab.model.User;
import com.inaya.collab.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create
    public User createUser(User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("User ID must be null for creation.");
        }
        return userRepository.save(user);
    }

    // Read
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Update
    public User updateUser(User updatedUser) {
        if (userRepository.existsById(updatedUser.getId())) {
            return userRepository.save(updatedUser);
        } else {
            throw new EntityNotFoundException("User not found with ID: " + updatedUser.getId());
        }
    }

    // Delete
    public void deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new EntityNotFoundException("User not found with ID: " + userId);
        }
    }

    // Find By Username
    public Optional<User> getByUsername(String username){
        return userRepository.findByUsername(username);
    }

}


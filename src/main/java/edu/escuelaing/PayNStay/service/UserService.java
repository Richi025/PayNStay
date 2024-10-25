package edu.escuelaing.PayNStay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.PayNStay.model.User;
import edu.escuelaing.PayNStay.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        user.setId(UUID.randomUUID());
        return userRepository.save(user);
    }

    public User updateUser(UUID id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setUserType(updatedUser.getUserType());
            return userRepository.save(user);
        }).orElse(null);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
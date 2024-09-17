package edu.escuelaing.PayNStay.Service.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.PayNStay.Repository.User.UserDto;
import edu.escuelaing.PayNStay.Repository.User.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDto getUserById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserDto createUser(UserDto user) {
        user.setId(UUID.randomUUID());
        return userRepository.save(user);
    }

    public UserDto updateUser(UUID id, UserDto updatedUser) {
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

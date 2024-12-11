package com.bootcamp.api_library.service;

import com.bootcamp.api_library.model.User;
import com.bootcamp.api_library.respository.UserRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public void addUser(User newUser) {
        userRepository.save(newUser);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public User updateUser(UUID id, User userDetails) {
        Optional<User> foundUser = userRepository.findById(id);

        if(foundUser.isPresent()) {
            User user = foundUser.get();
            user.setFullName(userDetails.getFullName());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());

            return userRepository.save(user);
        }

        throw new RuntimeException("User with email " + userDetails.getEmail() + " not found.");
    }

}

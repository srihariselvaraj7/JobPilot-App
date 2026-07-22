package com.srihari.jobpilot.service;
import com.srihari.jobpilot.entity.Role;
import com.srihari.jobpilot.entity.User;
import com.srihari.jobpilot.exception.UserAlreadyExistsException;
import com.srihari.jobpilot.exception.UserNotFoundException;
import com.srihari.jobpilot.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user){

        if(userRepository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsException(
                    "Email already exists: " + user.getEmail());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: "+id));
    }

    public User updateUser(User updatedUser, int id){
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: "+id));

        if (!existingUser.getEmail().equals(updatedUser.getEmail())
                && userRepository.existsByEmail(updatedUser.getEmail())) {
            throw new UserAlreadyExistsException(
                    "Email already exists: " + updatedUser.getEmail()
            );
        }

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhone(updatedUser.getPhone());

        if (updatedUser.getPassword() != null &&
                !updatedUser.getPassword().isBlank()) {
            existingUser.setPassword(
                    passwordEncoder.encode(updatedUser.getPassword())
            );
        }

        return userRepository.save(existingUser);
    }

    public void deleteUserById(int id){
        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id: "+id));

        userRepository.delete(existingUser);
    }
}

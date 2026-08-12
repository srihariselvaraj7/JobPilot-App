package com.srihari.jobpilot.service;

import com.srihari.jobpilot.dto.UserRequestDto;
import com.srihari.jobpilot.dto.UserResponseDto;
import com.srihari.jobpilot.entity.Role;
import com.srihari.jobpilot.entity.User;
import com.srihari.jobpilot.exception.UserAlreadyExistsException;
import com.srihari.jobpilot.exception.UserNotFoundException;
import com.srihari.jobpilot.mapper.UserMapper;
import com.srihari.jobpilot.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public UserResponseDto saveUser(UserRequestDto userRequestDto) {

        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new UserAlreadyExistsException(
                    "Email already exists: " + userRequestDto.getEmail());
        }

        User user = userMapper.toEntity(userRequestDto);
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );
        user.setRole(Role.USER);
        User savedUser = userRepository.save(user);
        return userMapper.toResponseDto(savedUser);
    }

    public List<UserResponseDto> getAllUser() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDto)
                .toList();
    }

    public UserResponseDto getUserById(int id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: " + id));

        return userMapper.toResponseDto(user);
    }

    public UserResponseDto updateUser(UserRequestDto updatedUserDto, int id) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: " + id));

        if (!existingUser.getEmail().equals(updatedUserDto.getEmail())
                && userRepository.existsByEmail(updatedUserDto.getEmail())) {

            throw new UserAlreadyExistsException(
                    "Email already exists: " + updatedUserDto.getEmail()
            );
        }

        existingUser.setName(updatedUserDto.getName());
        existingUser.setEmail(updatedUserDto.getEmail());
        existingUser.setPhone(updatedUserDto.getPhone());

        if (updatedUserDto.getPassword() != null
                && !updatedUserDto.getPassword().isBlank()) {

            existingUser.setPassword(
                    passwordEncoder.encode(updatedUserDto.getPassword())
            );
        }

        User savedUser = userRepository.save(existingUser);
        return userMapper.toResponseDto(savedUser);
    }

    public void deleteUserById(int id) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: " + id));

        userRepository.delete(existingUser);
    }
}
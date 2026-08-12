package com.srihari.jobpilot.controller;

import com.srihari.jobpilot.dto.UserRequestDto;
import com.srihari.jobpilot.dto.UserResponseDto;
import com.srihari.jobpilot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> saveUser(
            @Valid @RequestBody UserRequestDto userRequestDto) {

        UserResponseDto response = userService.saveUser(userRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUser() {

        List<UserResponseDto> users = userService.getAllUser();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(
            @PathVariable int id) {

        UserResponseDto user = userService.getUserById(id);

        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable int id,
            @Valid @RequestBody UserRequestDto updatedUserDto) {

        UserResponseDto updatedUser =
                userService.updateUser(updatedUserDto, id);

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(
            @PathVariable int id) {

        userService.deleteUserById(id);

        return ResponseEntity.noContent().build();
    }
}
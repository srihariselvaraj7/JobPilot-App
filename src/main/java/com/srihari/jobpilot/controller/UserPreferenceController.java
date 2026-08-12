package com.srihari.jobpilot.controller;

import com.srihari.jobpilot.dto.UserPreferenceRequestDto;
import com.srihari.jobpilot.dto.UserPreferenceResponseDto;
import com.srihari.jobpilot.service.UserPreferenceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserPreferenceController {

    private final UserPreferenceService userPreferenceService;

    public UserPreferenceController(
            UserPreferenceService userPreferenceService) {

        this.userPreferenceService = userPreferenceService;
    }

    @PostMapping("/{userId}/preferences")
    public ResponseEntity<UserPreferenceResponseDto> saveUserPreference(
            @PathVariable Integer userId,
            @Valid @RequestBody UserPreferenceRequestDto requestDto) {

        UserPreferenceResponseDto response =
                userPreferenceService.saveUserPreference(
                        requestDto,
                        userId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{userId}/preferences")
    public ResponseEntity<UserPreferenceResponseDto>
    getUserPreferenceByUserId(
            @PathVariable Integer userId) {

        UserPreferenceResponseDto response =
                userPreferenceService.getUserPreferenceByUserId(userId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}/preferences")
    public ResponseEntity<UserPreferenceResponseDto>
    updateUserPreference(
            @PathVariable Integer userId,
            @Valid @RequestBody UserPreferenceRequestDto requestDto) {

        UserPreferenceResponseDto response =
                userPreferenceService.updateUserPreference(
                        requestDto,
                        userId);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}/preferences")
    public ResponseEntity<Void> deleteUserPreference(
            @PathVariable Integer userId) {

        userPreferenceService.deleteUserPreferenceById(userId);

        return ResponseEntity.noContent().build();
    }
}
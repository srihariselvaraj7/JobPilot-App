package com.srihari.jobpilot.controller;

import com.srihari.jobpilot.entity.UserPreference;
import com.srihari.jobpilot.service.UserPreferenceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserPreferenceController {

    private final UserPreferenceService userPreferenceService;

    public UserPreferenceController(UserPreferenceService userPreferenceService) {
        this.userPreferenceService = userPreferenceService;
    }

    @PostMapping("/{userId}/preferences")
    public ResponseEntity<UserPreference> saveUserPreference(
            @Valid @RequestBody UserPreference preference,
            @PathVariable Integer userId) {

        UserPreference savedPreference =
                userPreferenceService.saveUserPreference(preference, userId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedPreference);
    }

    @GetMapping("/{userId}/preferences")
    public ResponseEntity<UserPreference> getUserPreference(
            @PathVariable Integer userId) {

        return ResponseEntity.ok(
                userPreferenceService.getUserPreferenceByUserId(userId));
    }

    @PutMapping("/{userId}/preferences")
    public ResponseEntity<UserPreference> updateUserPreference(
            @Valid @RequestBody UserPreference preference,
            @PathVariable Integer userId) {

        return ResponseEntity.ok(
                userPreferenceService.updateUserPreference(preference, userId));
    }

    @DeleteMapping("/{userId}/preferences")
    public ResponseEntity<String> deleteUserPreference(
            @PathVariable Integer userId) {

        userPreferenceService.deleteUserPreferenceById(userId);

        return ResponseEntity.ok("User preference deleted successfully.");
    }
}
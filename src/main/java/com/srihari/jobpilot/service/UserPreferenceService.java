package com.srihari.jobpilot.service;

import com.srihari.jobpilot.dto.UserPreferenceRequestDto;
import com.srihari.jobpilot.dto.UserPreferenceResponseDto;
import com.srihari.jobpilot.entity.User;
import com.srihari.jobpilot.entity.UserPreference;
import com.srihari.jobpilot.exception.UserNotFoundException;
import com.srihari.jobpilot.exception.UserPreferenceAlreadyExistsException;
import com.srihari.jobpilot.exception.UserPreferenceNotFoundException;
import com.srihari.jobpilot.mapper.UserPreferenceMapper;
import com.srihari.jobpilot.repository.UserPreferenceRepository;
import com.srihari.jobpilot.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserPreferenceService {

    private final UserPreferenceRepository userPreferenceRepository;
    private final UserRepository userRepository;
    private final UserPreferenceMapper userPreferenceMapper;

    public UserPreferenceService(
            UserPreferenceRepository userPreferenceRepository,
            UserRepository userRepository,
            UserPreferenceMapper userPreferenceMapper) {

        this.userPreferenceRepository = userPreferenceRepository;
        this.userRepository = userRepository;
        this.userPreferenceMapper = userPreferenceMapper;
    }

    public UserPreferenceResponseDto saveUserPreference(
            UserPreferenceRequestDto requestDto,
            Integer userId) {

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "No user found with id " + userId));

        if (userPreferenceRepository.existsByUserId(userId)) {
            throw new UserPreferenceAlreadyExistsException(
                    "Preferences already exist for user id " + userId);
        }

        UserPreference preference =
                userPreferenceMapper.toEntity(requestDto);

        preference.setUser(existingUser);
        UserPreference savedPreference =
                userPreferenceRepository.save(preference);

        return userPreferenceMapper.toResponseDto(savedPreference);
    }

    public UserPreferenceResponseDto getUserPreferenceByUserId(
            Integer userId) {

        UserPreference preference =
                userPreferenceRepository.findByUserId(userId)
                        .orElseThrow(() ->
                                new UserPreferenceNotFoundException(
                                        "User preference not found with user id "
                                                + userId));

        return userPreferenceMapper.toResponseDto(preference);
    }

    public UserPreferenceResponseDto updateUserPreference(
            UserPreferenceRequestDto updatedPreference,
            Integer userId) {

        UserPreference existingPreference =
                userPreferenceRepository.findByUserId(userId)
                        .orElseThrow(() ->
                                new UserPreferenceNotFoundException(
                                        "User preference not found with user id "
                                                + userId));

        existingPreference.setPreferredRole(updatedPreference.getPreferredRole());
        existingPreference.setPreferredSkill(updatedPreference.getPreferredSkill());
        existingPreference.setPreferredLocation(updatedPreference.getPreferredLocation());
        existingPreference.setExpectedSalary(updatedPreference.getExpectedSalary());
        existingPreference.setEmploymentType(updatedPreference.getEmploymentType());
        existingPreference.setWorkMode(updatedPreference.getWorkMode());

        UserPreference savedPreference = userPreferenceRepository.save(existingPreference);
        return userPreferenceMapper.toResponseDto(savedPreference);
    }

    public void deleteUserPreferenceById(Integer userId) {

        UserPreference preference =
                userPreferenceRepository.findByUserId(userId)
                        .orElseThrow(() ->
                                new UserPreferenceNotFoundException(
                                        "User preference not found with user id "
                                                + userId));

        userPreferenceRepository.delete(preference);
    }
}
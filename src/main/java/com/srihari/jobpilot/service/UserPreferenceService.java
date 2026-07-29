package com.srihari.jobpilot.service;

import com.srihari.jobpilot.entity.User;
import com.srihari.jobpilot.entity.UserPreference;
import com.srihari.jobpilot.exception.UserNotFoundException;
import com.srihari.jobpilot.exception.UserPreferenceAlreadyExistsException;
import com.srihari.jobpilot.exception.UserPreferenceNotFoundException;
import com.srihari.jobpilot.repository.UserPreferenceRepository;
import com.srihari.jobpilot.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserPreferenceService {

    private final UserPreferenceRepository userPreferenceRepository;
    private final UserRepository userRepository;

    public UserPreferenceService(UserPreferenceRepository userPreferenceRepository,
                                 UserRepository userRepository){
        this.userPreferenceRepository=userPreferenceRepository;
        this.userRepository=userRepository;
    }

    public UserPreference saveUserPreference(UserPreference preference, Integer userId) {

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("No User found with id " + userId));

        if (userPreferenceRepository.existsByUserId(userId)) {
            throw new UserPreferenceAlreadyExistsException(
                    "Preferences already exist for user id " + userId);
        }

        preference.setUser(existingUser);
        existingUser.setUserPreference(preference);

        return userPreferenceRepository.save(preference);
    }

    public UserPreference getUserPreferenceByUserId(Integer userId){
        return userPreferenceRepository.findByUserId(userId).
                orElseThrow(()-> new UserPreferenceNotFoundException("User preference not found with user id "+userId));
    }

    public UserPreference updateUserPreference(UserPreference updatedPreference, Integer userId){

        UserPreference existingPreference = userPreferenceRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new UserPreferenceNotFoundException(
                                "User preference not found with user id " + userId));

        existingPreference.setPreferredRole(updatedPreference.getPreferredRole());
        existingPreference.setPreferredSkill(updatedPreference.getPreferredSkill());
        existingPreference.setPreferredLocation(updatedPreference.getPreferredLocation());
        existingPreference.setExpectedSalary(updatedPreference.getExpectedSalary());
        existingPreference.setEmploymentType(updatedPreference.getEmploymentType());
        existingPreference.setWorkMode(updatedPreference.getWorkMode());

        return userPreferenceRepository.save(existingPreference);
    }

    public void deleteUserPreferenceById(Integer userId){
        UserPreference deletePreference = userPreferenceRepository.findByUserId(userId)
                .orElseThrow(()-> new UserPreferenceNotFoundException(
                        "User preference not found with user id " + userId));
        userPreferenceRepository.delete(deletePreference);
    }
}

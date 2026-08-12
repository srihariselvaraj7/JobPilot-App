package com.srihari.jobpilot.mapper;

import com.srihari.jobpilot.dto.UserPreferenceRequestDto;
import com.srihari.jobpilot.dto.UserPreferenceResponseDto;
import com.srihari.jobpilot.entity.UserPreference;
import org.springframework.stereotype.Component;

@Component
public class UserPreferenceMapper {

    public UserPreference toEntity(UserPreferenceRequestDto requestDto) {

        UserPreference preference = new UserPreference();

        preference.setPreferredRole(requestDto.getPreferredRole());
        preference.setPreferredSkill(requestDto.getPreferredSkill());
        preference.setPreferredLocation(requestDto.getPreferredLocation());
        preference.setExpectedSalary(requestDto.getExpectedSalary());
        preference.setEmploymentType(requestDto.getEmploymentType());
        preference.setWorkMode(requestDto.getWorkMode());

        return preference;
    }

    public UserPreferenceResponseDto toResponseDto(UserPreference preference) {

        UserPreferenceResponseDto responseDto =
                new UserPreferenceResponseDto();

        responseDto.setId(preference.getId());
        responseDto.setPreferredRole(preference.getPreferredRole());
        responseDto.setPreferredSkill(preference.getPreferredSkill());
        responseDto.setPreferredLocation(preference.getPreferredLocation());
        responseDto.setExpectedSalary(preference.getExpectedSalary());
        responseDto.setEmploymentType(preference.getEmploymentType());
        responseDto.setWorkMode(preference.getWorkMode());
        responseDto.setCreatedAt(preference.getCreatedAt());
        responseDto.setUpdatedAt(preference.getUpdatedAt());

        return responseDto;
    }
}
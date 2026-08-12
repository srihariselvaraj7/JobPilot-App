package com.srihari.jobpilot.dto;

import com.srihari.jobpilot.entity.EmploymentType;
import com.srihari.jobpilot.entity.WorkMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPreferenceResponseDto {

    private int id;
    private String preferredRole;
    private String preferredSkill;
    private String preferredLocation;
    private String expectedSalary;
    private EmploymentType employmentType;
    private WorkMode workMode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
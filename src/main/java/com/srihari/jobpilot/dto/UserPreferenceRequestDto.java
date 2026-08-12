package com.srihari.jobpilot.dto;

import com.srihari.jobpilot.entity.EmploymentType;
import com.srihari.jobpilot.entity.WorkMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPreferenceRequestDto {

    @NotBlank(message = "Preferred role is required")
    @Size(max = 200, message = "Preferred role cannot exceed 200 characters")
    private String preferredRole;

    @NotBlank(message = "Preferred skill is required")
    @Size(max = 500, message = "Preferred skill cannot exceed 500 characters")
    private String preferredSkill;

    @NotBlank(message = "Preferred location is required")
    @Size(max = 300, message = "Preferred location cannot exceed 300 characters")
    private String preferredLocation;

    @NotBlank(message = "Expected salary is required")
    @Size(max = 50, message = "Expected salary cannot exceed 50 characters")
    private String expectedSalary;

    @NotNull(message = "Employment type is required")
    private EmploymentType employmentType;

    @NotNull(message = "Work mode is required")
    private WorkMode workMode;
}
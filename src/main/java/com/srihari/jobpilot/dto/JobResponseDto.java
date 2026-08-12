package com.srihari.jobpilot.dto;

import com.srihari.jobpilot.entity.EmploymentType;
import com.srihari.jobpilot.entity.Source;
import com.srihari.jobpilot.entity.WorkMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobResponseDto {

    private int id;
    private String title;
    private String company;
    private String location;
    private String experience;
    private String qualification;
    private String salary;
    private String skills;
    private String description;
    private EmploymentType employmentType;
    private WorkMode workMode;
    private String applyUrl;
    private Source source;
    private LocalDate postedDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

package com.srihari.jobpilot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "Job title is required")
    @Size(min = 3, max = 100, message = "Job title must be between 3 and 100 characters")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "Company name is required")
    @Size(min = 2, max = 100, message = "Company name must be between 2 and 100 characters")
    private String company;

    @Size(max = 100, message = "Location cannot exceed 100 characters")
    private String location;

    @Column(nullable = false)
    @NotBlank(message = "Experience is required")
    @Size(max = 50, message = "Experience cannot exceed 50 characters")
    private String experience;

    @Column(nullable = false)
    @NotBlank(message = "Qualification is required")
    @Size(max = 100, message = "Qualification cannot exceed 100 characters")
    private String qualification;

    @Size(max = 50, message = "Salary cannot exceed 50 characters")
    private String salary;

    @Column(nullable = false)
    @NotBlank(message = "Skills are required")
    @Size(max = 500, message = "Skills cannot exceed 500 characters")
    private String skills;

    @Column(columnDefinition = "TEXT", nullable = false)
    @NotBlank(message = "Job description is required")
    @Size(min = 20, max = 5000,
            message = "Job description must be between 20 and 5000 characters")
    private String description;

    @NotNull(message = "Employment type is required")
    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    @NotNull(message = "Work mode is required")
    @Enumerated(EnumType.STRING)
    private WorkMode workMode;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Apply URL is required")
    @Size(max = 500, message = "Apply URL cannot exceed 500 characters")
    private String applyUrl;

    @NotNull(message = "Job source is required")
    @Enumerated(EnumType.STRING)
    private Source source;

    @NotNull(message = "Posted date is required")
    private LocalDate postedDate;

    private LocalDate endDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

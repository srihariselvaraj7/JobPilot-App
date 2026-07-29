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

import java.time.LocalDateTime;

@Entity
@Table(name = "user_preferences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "Preferred role is required")
    @Size(max = 200, message = "Preferred role cannot exceed 200 characters")
    private String preferredRole;

    @Column(nullable = false)
    @NotBlank(message = "Preferred skill is required")
    @Size(max = 500, message = "Preferred skill cannot exceed 500 characters")
    private String preferredSkill;

    @Column(nullable = false)
    @NotBlank(message = "Preferred location is required")
    @Size(max = 300, message = "Preferred location cannot exceed 300 characters")
    private String preferredLocation;

    @Column(nullable = false)
    @NotBlank(message = "Expected salary is required")
    @Size(max = 50, message = "Expected salary cannot exceed 50 characters")
    private String expectedSalary;

    @NotNull(message = "Employment type is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmploymentType employmentType;

    @NotNull(message = "Work mode is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkMode workMode;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
}
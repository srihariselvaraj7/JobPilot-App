package com.srihari.jobpilot.controller;

import com.srihari.jobpilot.dto.JobRequestDto;
import com.srihari.jobpilot.dto.JobResponseDto;
import com.srihari.jobpilot.entity.EmploymentType;
import com.srihari.jobpilot.entity.WorkMode;
import com.srihari.jobpilot.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<JobResponseDto> saveJob(
            @Valid @RequestBody JobRequestDto jobRequestDto) {

        JobResponseDto savedJob = jobService.saveJob(jobRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedJob);
    }

    @GetMapping
    public ResponseEntity<List<JobResponseDto>> getAllJobs() {

        List<JobResponseDto> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponseDto> getJobById(@PathVariable int id) {
        JobResponseDto job = jobService.getJobById(id);
        return ResponseEntity.ok(job);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobResponseDto> updateJob(@PathVariable int id,
            @Valid @RequestBody JobRequestDto updatedJobRequest) {

        JobResponseDto updatedJob = jobService.updateJob(id, updatedJobRequest);

        return ResponseEntity.ok(updatedJob);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobById(
            @PathVariable int id) {

        jobService.deleteJobById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<JobResponseDto>> searchByTitle(
            @RequestParam String title) {

        return ResponseEntity.ok(
                jobService.searchByTitle(title)
        );
    }

    @GetMapping("/search/company")
    public ResponseEntity<List<JobResponseDto>> searchByCompany(
            @RequestParam String company) {

        return ResponseEntity.ok(
                jobService.searchByCompany(company)
        );
    }

    @GetMapping("/search/skills")
    public ResponseEntity<List<JobResponseDto>> searchBySkills(
            @RequestParam String skills) {

        return ResponseEntity.ok(
                jobService.searchBySkills(skills)
        );
    }

    @GetMapping("/search/location")
    public ResponseEntity<List<JobResponseDto>> searchByLocation(
            @RequestParam String location) {

        return ResponseEntity.ok(
                jobService.searchByLocation(location)
        );
    }

    @GetMapping("/search/experience")
    public ResponseEntity<List<JobResponseDto>> searchByExperience(
            @RequestParam String experience) {

        return ResponseEntity.ok(
                jobService.searchByExperience(experience)
        );
    }

    @GetMapping("/search/work-mode")
    public ResponseEntity<List<JobResponseDto>> searchByWorkMode(
            @RequestParam WorkMode workMode) {

        return ResponseEntity.ok(
                jobService.searchByWorkMode(workMode)
        );
    }

    @GetMapping("/search/employment-type")
    public ResponseEntity<List<JobResponseDto>> searchByEmploymentType(
            @RequestParam EmploymentType employmentType) {

        return ResponseEntity.ok(
                jobService.searchByEmploymentType(employmentType)
        );
    }
}
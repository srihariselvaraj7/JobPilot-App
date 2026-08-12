package com.srihari.jobpilot.controller;

import com.srihari.jobpilot.dto.JobRequestDto;
import com.srihari.jobpilot.dto.JobResponseDto;
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
}
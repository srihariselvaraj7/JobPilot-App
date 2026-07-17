package com.srihari.jobpilot.controller;
import com.srihari.jobpilot.entity.Job;
import com.srihari.jobpilot.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService){
        this.jobService=jobService;
    }

    @PostMapping
    public ResponseEntity<Job> saveJob(@RequestBody Job job) {
        Job savedJob = jobService.saveJob(job);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedJob);
    }

}

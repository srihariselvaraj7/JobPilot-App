package com.srihari.jobpilot.controller;
import com.srihari.jobpilot.entity.Job;
import com.srihari.jobpilot.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public List<Job> findAllJobs(){
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public Job findJobById(@PathVariable int id){
        return jobService.getJobById(id);
    }

}

package com.srihari.jobpilot.service;
import com.srihari.jobpilot.entity.Job;
import com.srihari.jobpilot.repository.JobRepository;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository){
        this.jobRepository=jobRepository;
    }

    public Job saveJob(Job job){
        return jobRepository.save(job);
    }
}

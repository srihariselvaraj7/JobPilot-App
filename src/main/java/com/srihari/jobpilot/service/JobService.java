package com.srihari.jobpilot.service;
import com.srihari.jobpilot.entity.Job;
import com.srihari.jobpilot.exception.JobNotFoundException;
import com.srihari.jobpilot.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository){
        this.jobRepository=jobRepository;
    }

    public Job saveJob(Job job){
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }

    public Job getJobById(int id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job not found with id: " + id));
    }

    public Job updateJob(int id, Job updatedJob){

        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() ->
                        new JobNotFoundException("Job not found with id: " + id));

        existingJob.setTitle(updatedJob.getTitle());
        existingJob.setCompany(updatedJob.getCompany());
        existingJob.setLocation(updatedJob.getLocation());
        existingJob.setSalary(updatedJob.getSalary());
        existingJob.setDescription(updatedJob.getDescription());
        existingJob.setEmploymentType(updatedJob.getEmploymentType());
        existingJob.setWorkMode(updatedJob.getWorkMode());

        return jobRepository.save(existingJob);
    }

    public void deleteJobById(int id){
        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() ->
                        new JobNotFoundException("Job not found with id: " + id));

        jobRepository.delete(existingJob);
    }
}

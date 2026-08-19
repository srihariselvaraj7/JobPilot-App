package com.srihari.jobpilot.service;

import com.srihari.jobpilot.dto.JobRequestDto;
import com.srihari.jobpilot.dto.JobResponseDto;
import com.srihari.jobpilot.entity.EmploymentType;
import com.srihari.jobpilot.entity.Job;
import com.srihari.jobpilot.entity.WorkMode;
import com.srihari.jobpilot.exception.JobAlreadyExistsException;
import com.srihari.jobpilot.exception.JobNotFoundException;
import com.srihari.jobpilot.mapper.JobMapper;
import com.srihari.jobpilot.repository.JobRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.srihari.jobpilot.specification.JobSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    public JobService(JobRepository jobRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
    }

    public JobResponseDto saveJob(JobRequestDto jobRequestDto) {

        if (jobRepository.existsByApplyUrl(jobRequestDto.getApplyUrl())) {
            throw new JobAlreadyExistsException(
                    "Job with apply URL already exists: "
                            + jobRequestDto.getApplyUrl()
            );
        }

        Job job = jobMapper.toEntity(jobRequestDto);
        Job savedJob = jobRepository.save(job);

        return jobMapper.toResponseDto(savedJob);
    }

    public List<JobResponseDto> getAllJobs() {

        return jobRepository.findAll()
                .stream()
                .map(jobMapper::toResponseDto)
                .toList();
    }

    public JobResponseDto getJobById(int id) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new JobNotFoundException(
                                "Job not found with id: " + id
                        ));

        return jobMapper.toResponseDto(job);
    }

    public JobResponseDto updateJob(
            int id,
            JobRequestDto updatedJobRequest) {

        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() ->
                        new JobNotFoundException(
                                "Job not found with id: " + id
                        ));

        if (!existingJob.getApplyUrl()
                .equals(updatedJobRequest.getApplyUrl())
                && jobRepository.existsByApplyUrl(
                updatedJobRequest.getApplyUrl())) {

            throw new JobAlreadyExistsException(
                    "Job with apply URL already exists: "
                            + updatedJobRequest.getApplyUrl()
            );
        }

        existingJob.setTitle(updatedJobRequest.getTitle());
        existingJob.setCompany(updatedJobRequest.getCompany());
        existingJob.setLocation(updatedJobRequest.getLocation());
        existingJob.setExperience(updatedJobRequest.getExperience());
        existingJob.setQualification(updatedJobRequest.getQualification());
        existingJob.setSalary(updatedJobRequest.getSalary());
        existingJob.setSkills(updatedJobRequest.getSkills());
        existingJob.setDescription(updatedJobRequest.getDescription());
        existingJob.setEmploymentType(updatedJobRequest.getEmploymentType());
        existingJob.setWorkMode(updatedJobRequest.getWorkMode());
        existingJob.setApplyUrl(updatedJobRequest.getApplyUrl());
        existingJob.setSource(updatedJobRequest.getSource());
        existingJob.setPostedDate(updatedJobRequest.getPostedDate());
        existingJob.setEndDate(updatedJobRequest.getEndDate());

        Job updatedJob = jobRepository.save(existingJob);
        return jobMapper.toResponseDto(updatedJob);
    }

    public void deleteJobById(int id) {
        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() ->
                        new JobNotFoundException(
                                "Job not found with id: " + id
                        ));

        jobRepository.delete(existingJob);
    }

    public List<JobResponseDto> searchByTitle(String title) {

        return jobRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(jobMapper::toResponseDto)
                .toList();
    }

    public List<JobResponseDto> searchByCompany(String company) {

        return jobRepository.findByCompanyContainingIgnoreCase(company)
                .stream()
                .map(jobMapper::toResponseDto)
                .toList();
    }

    public List<JobResponseDto> searchBySkills(String skills) {

        return jobRepository.findBySkillsContainingIgnoreCase(skills)
                .stream()
                .map(jobMapper::toResponseDto)
                .toList();
    }

    public List<JobResponseDto> searchByLocation(String location) {

        return jobRepository.findByLocationContainingIgnoreCase(location)
                .stream()
                .map(jobMapper::toResponseDto)
                .toList();
    }

    public List<JobResponseDto> searchByExperience(String experience) {

        return jobRepository.findByExperienceContainingIgnoreCase(experience)
                .stream()
                .map(jobMapper::toResponseDto)
                .toList();
    }

    public List<JobResponseDto> searchByWorkMode(WorkMode workMode) {

        return jobRepository.findByWorkMode(workMode)
                .stream()
                .map(jobMapper::toResponseDto)
                .toList();
    }

    public List<JobResponseDto> searchByEmploymentType(
            EmploymentType employmentType) {

        return jobRepository.findByEmploymentType(employmentType)
                .stream()
                .map(jobMapper::toResponseDto)
                .toList();
    }

    public Page<JobResponseDto> getAllJobs(Pageable pageable) {
        return jobRepository.findAll(pageable)
                .map(jobMapper::toResponseDto);
    }

    public Page<JobResponseDto> searchJobs(
            String title,
            String company,
            String skills,
            String location,
            String experience,
            WorkMode workMode,
            EmploymentType employmentType,
            Pageable pageable) {

        Specification<Job> specification = Specification.where(null);

        if (title != null && !title.isBlank()) {
            specification = specification.and(
                    JobSpecification.hasTitle(title)
            );
        }

        if (company != null && !company.isBlank()) {
            specification = specification.and(
                    JobSpecification.hasCompany(company)
            );
        }

        if (skills != null && !skills.isBlank()) {
            specification = specification.and(
                    JobSpecification.hasSkills(skills)
            );
        }

        if (location != null && !location.isBlank()) {
            specification = specification.and(
                    JobSpecification.hasLocation(location)
            );
        }

        if (experience != null && !experience.isBlank()) {
            specification = specification.and(
                    JobSpecification.hasExperience(experience)
            );
        }

        if (workMode != null) {
            specification = specification.and(
                    JobSpecification.hasWorkMode(workMode)
            );
        }

        if (employmentType != null) {
            specification = specification.and(
                    JobSpecification.hasEmploymentType(employmentType)
            );
        }

        return jobRepository
                .findAll(specification, pageable)
                .map(jobMapper::toResponseDto);
    }
}
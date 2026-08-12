package com.srihari.jobpilot.mapper;

import com.srihari.jobpilot.dto.JobRequestDto;
import com.srihari.jobpilot.dto.JobResponseDto;
import com.srihari.jobpilot.entity.Job;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    public Job toEntity(JobRequestDto requestDto){

        Job job = new Job();

        job.setTitle(requestDto.getTitle());
        job.setCompany(requestDto.getCompany());
        job.setLocation(requestDto.getLocation());
        job.setExperience(requestDto.getExperience());
        job.setQualification(requestDto.getQualification());
        job.setSalary(requestDto.getSalary());
        job.setSkills(requestDto.getSkills());
        job.setDescription(requestDto.getDescription());
        job.setEmploymentType(requestDto.getEmploymentType());
        job.setWorkMode(requestDto.getWorkMode());
        job.setApplyUrl(requestDto.getApplyUrl());
        job.setSource(requestDto.getSource());
        job.setPostedDate(requestDto.getPostedDate());
        job.setEndDate(requestDto.getEndDate());

        return job;
    }

    public JobResponseDto toResponseDto(Job job){

        JobResponseDto responseDto = new JobResponseDto();

        responseDto.setId(job.getId());
        responseDto.setTitle(job.getTitle());
        responseDto.setCompany(job.getCompany());
        responseDto.setLocation(job.getLocation());
        responseDto.setExperience(job.getExperience());
        responseDto.setQualification(job.getQualification());
        responseDto.setSalary(job.getSalary());
        responseDto.setSkills(job.getSkills());
        responseDto.setDescription(job.getDescription());
        responseDto.setEmploymentType(job.getEmploymentType());
        responseDto.setWorkMode(job.getWorkMode());
        responseDto.setApplyUrl(job.getApplyUrl());
        responseDto.setSource(job.getSource());
        responseDto.setPostedDate(job.getPostedDate());
        responseDto.setEndDate(job.getEndDate());
        responseDto.setCreatedAt(job.getCreatedAt());
        responseDto.setUpdatedAt(job.getUpdatedAt());

        return responseDto;
    }
}

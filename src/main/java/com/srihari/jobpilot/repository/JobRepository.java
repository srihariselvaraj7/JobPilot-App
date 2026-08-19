package com.srihari.jobpilot.repository;

import com.srihari.jobpilot.entity.EmploymentType;
import com.srihari.jobpilot.entity.Job;
import com.srihari.jobpilot.entity.WorkMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer>, JpaSpecificationExecutor<Job>{

    boolean existsByApplyUrl(String applyUrl);
    List<Job> findByTitleContainingIgnoreCase(String title);
    List<Job> findByCompanyContainingIgnoreCase(String company);
    List<Job> findBySkillsContainingIgnoreCase(String skills);
    List<Job> findByLocationContainingIgnoreCase(String location);
    List<Job> findByExperienceContainingIgnoreCase(String experience);
    List<Job> findByWorkMode(WorkMode workMode);
    List<Job> findByEmploymentType(EmploymentType employmentType);
}
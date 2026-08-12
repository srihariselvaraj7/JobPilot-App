package com.srihari.jobpilot.repository;

import com.srihari.jobpilot.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Integer> {
    boolean existsByApplyUrl(String applyUrl);
}

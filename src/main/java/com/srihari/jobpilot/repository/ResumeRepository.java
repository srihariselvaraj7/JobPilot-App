package com.srihari.jobpilot.repository;

import com.srihari.jobpilot.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume,Integer> {
     Optional<Resume> findByUserId(Integer userId);
}

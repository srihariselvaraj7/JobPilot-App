package com.srihari.jobpilot.repository;

import com.srihari.jobpilot.entity.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPreferenceRepository extends JpaRepository<UserPreference,Integer> {

    Optional<UserPreference> findByUserId(Integer userId);
    boolean existsByUserId(Integer userId);
}

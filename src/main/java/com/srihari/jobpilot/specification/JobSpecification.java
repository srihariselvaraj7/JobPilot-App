package com.srihari.jobpilot.specification;

import com.srihari.jobpilot.entity.EmploymentType;
import com.srihari.jobpilot.entity.Job;
import com.srihari.jobpilot.entity.WorkMode;
import org.springframework.data.jpa.domain.Specification;

public class JobSpecification {

    public static Specification<Job> hasTitle(String title) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("title")),
                        "%" + title.toLowerCase() + "%"
                );
    }

    public static Specification<Job> hasCompany(String company) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("company")),
                        "%" + company.toLowerCase() + "%"
                );
    }

    public static Specification<Job> hasSkills(String skills) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("skills")),
                        "%" + skills.toLowerCase() + "%"
                );
    }

    public static Specification<Job> hasLocation(String location) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("location")),
                        "%" + location.toLowerCase() + "%"
                );
    }

    public static Specification<Job> hasExperience(String experience) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("experience")),
                        "%" + experience.toLowerCase() + "%"
                );
    }

    public static Specification<Job> hasWorkMode(WorkMode workMode) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("workMode"),
                        workMode
                );
    }

    public static Specification<Job> hasEmploymentType(
            EmploymentType employmentType) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("employmentType"),
                        employmentType
                );
    }
}
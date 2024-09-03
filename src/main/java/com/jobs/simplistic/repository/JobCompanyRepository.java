package com.jobs.simplistic.repository;

import com.jobs.simplistic.entity.JobCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Nikolay Boyko
 */
public interface JobCompanyRepository extends JpaRepository<JobCompanyEntity, Integer> {

    JobCompanyEntity findByCompanyName(String companyName);
}

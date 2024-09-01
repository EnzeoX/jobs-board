package com.faceit.faceittest.repository;

import com.faceit.faceittest.entity.JobCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Nikolay Boyko
 */
public interface JobCompanyRepository extends JpaRepository<JobCompanyEntity, Integer> {

    JobCompanyEntity findByCompanyName(String companyName);
}

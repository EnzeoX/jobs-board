package com.faceit.faceittest.repository;

import com.faceit.faceittest.entity.JobCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Nikolay Boyko
 */
public interface JobCompanyRepository extends JpaRepository<JobCompanyEntity, Integer> {

    Optional<JobCompanyEntity> findByCompanyName(String companyName);
}

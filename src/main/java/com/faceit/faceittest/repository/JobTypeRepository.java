package com.faceit.faceittest.repository;

import com.faceit.faceittest.entity.JobTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Nikolay Boyko
 */
public interface JobTypeRepository extends JpaRepository<JobTypeEntity, Integer> {
}

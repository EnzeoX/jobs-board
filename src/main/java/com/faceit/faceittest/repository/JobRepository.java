package com.faceit.faceittest.repository;

import com.faceit.faceittest.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Nikolay Boyko
 */

public interface JobRepository extends JpaRepository<JobEntity, Integer> {
}

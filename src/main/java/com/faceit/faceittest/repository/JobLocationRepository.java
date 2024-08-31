package com.faceit.faceittest.repository;

import com.faceit.faceittest.entity.JobLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Nikolay Boyko
 */
public interface JobLocationRepository extends JpaRepository<JobLocationEntity, Integer> {
}

package com.faceit.faceittest.repository;

import com.faceit.faceittest.entity.JobTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Nikolay Boyko
 */
public interface JobTagRepository extends JpaRepository<JobTagEntity, Integer> {
}

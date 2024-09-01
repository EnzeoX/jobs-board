package com.faceit.faceittest.repository;

import com.faceit.faceittest.entity.JobTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Nikolay Boyko
 */
public interface JobTagRepository extends JpaRepository<JobTagEntity, Integer> {

    @Query(value = "select * from job_tags where tag=:tagName", nativeQuery = true)
    JobTagEntity findByTagName(String tagName);
}

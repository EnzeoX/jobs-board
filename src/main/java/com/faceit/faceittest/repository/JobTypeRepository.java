package com.faceit.faceittest.repository;

import com.faceit.faceittest.entity.JobTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Nikolay Boyko
 */
public interface JobTypeRepository extends JpaRepository<JobTypeEntity, Integer> {

    @Query(value = "select * from job_types where type_name=:type", nativeQuery = true)
    JobTypeEntity findByTypeName(String type);
}

package com.jobs.simplistic.repository;

import com.jobs.simplistic.entity.JobTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Nikolay Boyko
 */
public interface JobTypeRepository extends JpaRepository<JobTypeEntity, Integer> {

    @Query(value = "select * from job_types where type_name = :typeName", nativeQuery = true)
    JobTypeEntity findByTypeName(String typeName);
}

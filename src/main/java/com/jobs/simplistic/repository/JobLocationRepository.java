package com.jobs.simplistic.repository;

import com.jobs.simplistic.entity.JobLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Nikolay Boyko
 */
public interface JobLocationRepository extends JpaRepository<JobLocationEntity, Integer> {

    JobLocationEntity findByLocationName(String location);
}

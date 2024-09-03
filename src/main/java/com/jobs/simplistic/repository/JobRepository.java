package com.jobs.simplistic.repository;

import com.jobs.simplistic.dto.ProjectionCityStatistics;
import com.jobs.simplistic.entity.JobEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * @author Nikolay Boyko
 */

public interface JobRepository extends JpaRepository<JobEntity, Integer> {

    @Query(value = "select * from jobs where slug =:slug and title=:title and remote=:remote and url=:url", nativeQuery = true)
    JobEntity checkUnique(String slug, String title, boolean remote, String url);

    JobEntity findJobEntityBySlug(String slug);

    @Query(value = "SELECT jl.location, COUNT(j.job_id) AS job_count " +
            "FROM locations l " +
            "JOIN job_locations jl ON l.location_id = jl.location_id " +
            "JOIN jobs j ON l.job_id = j.job_id " +
            "GROUP BY jl.location " +
            "ORDER BY job_count DESC;", nativeQuery = true)
    List<ProjectionCityStatistics> getStatisticsByCityDesc();

    @Query(value = "SELECT jl.location, COUNT(j.job_id) AS job_count " +
            "FROM locations l " +
            "JOIN job_locations jl ON l.location_id = jl.location_id " +
            "JOIN jobs j ON l.job_id = j.job_id " +
            "GROUP BY jl.location " +
            "ORDER BY job_count ASC;", nativeQuery = true)
    List<ProjectionCityStatistics> getStatisticsByCityAsc();

    @Modifying
    @Transactional
    @Query("DELETE FROM JobEntity j WHERE j.createdAt <= :date")
    int deleteOlderThanDate(@Param("date") Date date);
}

package com.faceit.faceittest.repository;

import com.faceit.faceittest.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Nikolay Boyko
 */

public interface JobRepository extends JpaRepository<JobEntity, Integer> {

    @Query(value = "select * from jobs where slug =:slug and title=:title and remote=:remote and url=:url", nativeQuery = true)
    JobEntity checkUnique(String slug, String title, boolean remote, String url);

    JobEntity findJobEntityBySlug(String slug);
}

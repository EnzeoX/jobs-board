package com.faceit.faceittest.repository;

import com.faceit.faceittest.entity.JobTagEntity;
import com.faceit.faceittest.entity.JobTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * @author Nikolay Boyko
 */
public interface JobTagRepository extends JpaRepository<JobTagEntity, Integer> {

    @Query(value = "SELECT * FROM job_tags jt WHERE jt.tag IN :tags", nativeQuery = true)
    Set<JobTagEntity> findAllByTagInIterable(@Param("tags") List<String> tags);
}

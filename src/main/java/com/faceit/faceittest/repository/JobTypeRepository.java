package com.faceit.faceittest.repository;

import com.faceit.faceittest.entity.JobTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * @author Nikolay Boyko
 */
public interface JobTypeRepository extends JpaRepository<JobTypeEntity, Integer> {

    @Query(value = "SELECT * FROM job_types jt WHERE jt.type IN :types", nativeQuery = true)
    Set<JobTypeEntity> findAllByTypeInIterable(@Param("types") List<String> types);
}

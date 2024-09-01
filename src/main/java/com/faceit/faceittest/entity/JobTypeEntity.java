package com.faceit.faceittest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Nikolay Boyko
 */

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "job_types")
public class JobTypeEntity {

    @Id
    @Column(name = "type_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "type", nullable = false, unique = true)
    private String type;

    @ManyToMany(targetEntity = JobEntity.class,fetch = FetchType.LAZY)
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "types",
//            joinColumns = @JoinColumn(name = "type_id"),
//            inverseJoinColumns = @JoinColumn(name = "job_id")
//    )
    private List<JobEntity> jobs;
}

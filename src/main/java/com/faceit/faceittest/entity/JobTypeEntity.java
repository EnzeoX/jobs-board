package com.faceit.faceittest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

/**
 * @author Nikolay Boyko
 */

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "job_types")
@EqualsAndHashCode(exclude = {"id", "jobs"})
public class JobTypeEntity {

    @Id
    @Column(name = "type_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "type", nullable = false, unique = true)
    private String type = "";

    @ManyToMany(targetEntity = JobEntity.class,fetch = FetchType.LAZY)
//    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "types",
            joinColumns = @JoinColumn(name = "type_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id")
    )
    private Set<JobEntity> jobs;
}

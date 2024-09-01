package com.faceit.faceittest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Nikolay Boyko
 */

@Data
@Entity
@Builder
@ToString
@Table(name = "jobs")
@NoArgsConstructor
@AllArgsConstructor
public class JobEntity {

    @Id
    @Column(name = "job_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "slug")
    private String slug = "";

    @Column(name = "title", nullable = false)
    private String title = "";

    @Column(name = "description", nullable = false, length = 50000)
    private String description = "";

    @Column(name = "remote")
    private boolean remote;

    @Column(name = "url")
    private String url = "";

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity=JobCompanyEntity.class)
//    @JoinColumn(name = "company_id")
    private JobCompanyEntity company;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity=JobLocationEntity.class)
//    @JoinColumn(name = "location_id")
    private JobLocationEntity location;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity=JobTagEntity.class)
//    @JoinTable(
//            name = "tags",
//            joinColumns = @JoinColumn(name = "job_id"),
//            inverseJoinColumns = @JoinColumn(name = "tag_id")
//    )
    private Set<JobTagEntity> tags;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "types",
//            joinColumns = @JoinColumn(name = "job_id"),
//            inverseJoinColumns = @JoinColumn(name = "type_id")
//    )
    private Set<JobTypeEntity> types;
}

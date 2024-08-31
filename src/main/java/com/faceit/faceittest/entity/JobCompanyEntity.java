package com.faceit.faceittest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author Nikolay Boyko
 */

@Data
@Entity
@Builder
@ToString(exclude = "jobs")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "job_companies")
public class JobCompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id", unique = true, nullable = false)
    private int id;

    @Column(name = "company")
    private String companyName;

    //    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
            name = "companies",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id")
    )
    private List<JobEntity> jobs;
}

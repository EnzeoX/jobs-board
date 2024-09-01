package com.faceit.faceittest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_id", unique = true, nullable = false)
    private int id;

    @Column(name = "company")
    private String companyName = "";

    //    @ManyToMany(targetEntity = JobEntity.class, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "companies",
//            joinColumns = @JoinColumn(name = "company_id"),
//            inverseJoinColumns = @JoinColumn(name = "job_id")
//    )
    @JsonIgnore
    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private List<JobEntity> jobs = new ArrayList<>();
}

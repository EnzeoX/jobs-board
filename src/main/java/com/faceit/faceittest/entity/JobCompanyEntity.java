package com.faceit.faceittest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Nikolay Boyko
 */

@Data
@Entity
@Builder
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

    @JsonIgnore
    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private List<JobEntity> jobs = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobCompanyEntity that = (JobCompanyEntity) o;
        return companyName.equals(that.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName);
    }
}

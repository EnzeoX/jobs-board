package com.faceit.faceittest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikolay Boyko
 */

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "jobs")
@Table(name = "job_locations")
public class JobLocationEntity {

    @Id
    @Column(name = "location_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "location", unique = true, nullable = false)
    private String locationName = "";


    @OneToMany(mappedBy = "location", fetch = FetchType.EAGER)
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "locations",
//            joinColumns = @JoinColumn(name = "location_id"),
//            inverseJoinColumns = @JoinColumn(name = "job_id")
//    )
    private List<JobEntity> jobs = new ArrayList<>();
}

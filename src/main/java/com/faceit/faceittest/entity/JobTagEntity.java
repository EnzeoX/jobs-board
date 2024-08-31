package com.faceit.faceittest.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

/**
 * @author Nikolay Boyko
 */

@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "job_tags")
public class JobTagEntity {

    @Id
    @Column(name = "tag_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tag", unique = true, nullable = false)
    private String tag;

    //    @ManyToMany(mappedBy = "tags", cascade=CascadeType.ALL)
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
            name = "tags",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id")
    )
    private List<JobEntity> jobs;

}

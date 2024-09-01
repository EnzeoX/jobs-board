package com.faceit.faceittest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Nikolay Boyko
 */

@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "job_tags", uniqueConstraints = {
        @UniqueConstraint(columnNames = "tag")
})
@EqualsAndHashCode(exclude = "jobs")
public class JobTagEntity {

    @Id
    @Column(name = "tag_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tag", unique = true, nullable = false)
    private String tagName = "";

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "tags",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id")
    )
    private Set<JobEntity> jobs = new HashSet<>();

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        JobTagEntity tagEntity = (JobTagEntity) o;
//        return tagName.equals(tagEntity.tagName);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(tagName);
//    }
}

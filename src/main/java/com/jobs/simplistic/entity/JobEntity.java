package com.jobs.simplistic.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

/**
 * @author Nikolay Boyko
 */


@Getter
@Setter
@Entity
@Builder
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

    @Column(name = "added_at")
    private Date addedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "companies",
            joinColumns = @JoinColumn(name = "job_id", referencedColumnName = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id", referencedColumnName = "company_id"))
    private JobCompanyEntity company = new JobCompanyEntity();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "locations",
            joinColumns = @JoinColumn(name = "job_id", referencedColumnName = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id", referencedColumnName = "location_id"))
    private JobLocationEntity location = new JobLocationEntity();

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(
            name = "tags",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<JobTagEntity> tags = new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(
            name = "types",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    private Set<JobTypeEntity> types = new HashSet<>();

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getSlug() {
//        return slug;
//    }
//
//    public void setSlug(String slug) {
//        this.slug = slug;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public boolean isRemote() {
//        return remote;
//    }
//
//    public void setRemote(boolean remote) {
//        this.remote = remote;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public JobCompanyEntity getCompany() {
//        return company;
//    }
//
//    public void setCompany(JobCompanyEntity company) {
//        this.company = company;
//        this.company.getJobs().add(this);
//    }
//
//    public JobLocationEntity getLocation() {
//        return location;
//    }
//
//    public void setLocation(JobLocationEntity location) {
//        this.location = location;
//        this.location.getJobs().add(this);
//    }
//
//    public Set<JobTagEntity> getTags() {
//        return tags;
//    }
//
//
//    public void setTags(Set<JobTagEntity> tags) {
//        this.tags = tags;
//    }
//
//    public Set<JobTypeEntity> getTypes() {
//        return types;
//    }
//
//    public void setTypes(Set<JobTypeEntity> types) {
//        this.types = types;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobEntity jobEntity = (JobEntity) o;
        return remote == jobEntity.remote
                && slug.equals(jobEntity.slug)
                && title.equals(jobEntity.title)
                && description.equals(jobEntity.description)
                && url.equals(jobEntity.url)
                && createdAt.equals(jobEntity.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slug, title, description, remote, url, createdAt);
    }
}

package com.faceit.faceittest.utils;

import com.faceit.faceittest.entity.*;
import com.faceit.faceittest.models.Job;
import com.faceit.faceittest.models.JobsData;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nikolay Boyko
 */

@Slf4j
public class PojoMapper {

    public static Job jobEntityToJob(JobEntity jobEntity) {
        return Job.builder()
                .slug(jobEntity.getSlug())
                .companyName(jobEntity.getCompany().getCompanyName())
                .location(jobEntity.getLocation().getLocationName())
                .title(jobEntity.getTitle())
                .description(jobEntity.getDescription())
                .remote(jobEntity.isRemote())
                .url(jobEntity.getUrl())
                .jobTags(jobEntity.getTags().stream()
                        .map(JobTagEntity::getTagName)
                        .toList()
                )
                .jobTypes(jobEntity.getTypes().stream()
                        .map(JobTypeEntity::getTypeName)
                        .toList()
                )
                .createdAt(jobEntity.getCreatedAt())
                .build();
    }

    public static List<JobEntity> jobDataToJobEntities(JobsData data) {
        if (data == null) return new ArrayList<>();
//        // try with streams
        return data.getData().stream()
                .map(job -> {
                    JobEntity jobEntity = new JobEntity();
                    jobEntity.setSlug(job.getSlug());
                    jobEntity.setTitle(job.getTitle());
                    jobEntity.setDescription(job.getDescription());
                    jobEntity.setRemote(job.isRemote());
                    jobEntity.setUrl(job.getUrl());
                    // Here, need to fix Date
                    jobEntity.setCreatedAt(new Date(1000L * job.getCreatedAt().getTime()));

                    JobCompanyEntity jobCompanyEntity = new JobCompanyEntity();
                    jobCompanyEntity.setCompanyName(job.getCompanyName());
                    jobEntity.setCompany(jobCompanyEntity);

                    JobLocationEntity jobLocationEntity = new JobLocationEntity();
                    jobLocationEntity.setLocationName(job.getLocation());
                    jobEntity.setLocation(jobLocationEntity);

                    jobEntity.setTypes(job.getJobTypes().stream()
                            .map(type -> {
                                JobTypeEntity typeEntity = new JobTypeEntity();
                                typeEntity.setTypeName(type);
                                return typeEntity;
                            })
                            .collect(Collectors.toSet()));

                    jobEntity.setTags(job.getJobTags().stream()
                            .map(tags -> {
                                JobTagEntity tagEntity = new JobTagEntity();
                                tagEntity.setTagName(tags);
                                return tagEntity;
                            })
                            .collect(Collectors.toSet()));

                    return jobEntity;
                }).toList();


//        List<JobEntity> jobEntities = new ArrayList<>();
//        for (Job job : data.getData()) {
//            if (job == null) {
//                log.warn("There is a null job data");
//                continue;
//            }
//            JobEntity jobEntity = new JobEntity();
//            jobEntities.add(jobEntity);
//
//            jobEntity.setSlug(job.getSlug());
//            jobEntity.setTitle(job.getTitle());
//            jobEntity.setDescription(job.getDescription());
//            jobEntity.setRemote(job.isRemote());
//            jobEntity.setUrl(job.getUrl());
//            jobEntity.setCreatedAt(job.getCreatedAt());
//
//            // Set company
//            JobCompanyEntity jobCompanyEntity = new JobCompanyEntity();
//            jobCompanyEntity.setCompanyName(job.getCompanyName());
//            jobEntity.setCompany(jobCompanyEntity);
//
//            // Set location
//            JobLocationEntity jobLocationEntity = new JobLocationEntity();
//            jobLocationEntity.setLocationName(job.getLocation());
//            jobEntity.setLocation(jobLocationEntity);
//
//            // Set tags
//            Set<JobTagEntity> jobTagEntityList = new HashSet<>();
//            for (String jobTag : job.getJobTags()) {
//                JobTagEntity jobTagEntity = new JobTagEntity();
//                jobTagEntity.setTagName(jobTag);
//                jobTagEntityList.add(jobTagEntity);
//            }
//            jobEntity.setTags(jobTagEntityList);
//
//            // Set types
//            Set<JobTypeEntity> jobTypeEntitiesList = new HashSet<>();
//            for (String jobType : job.getJobTypes()) {
//                JobTypeEntity jobTypeEntity = new JobTypeEntity();
//                jobTypeEntity.setTypeName(jobType);
//                jobTypeEntitiesList.add(jobTypeEntity);
//            }
//            jobEntity.setTypes(jobTypeEntitiesList);
//        }
//        return jobEntities;
    }
}

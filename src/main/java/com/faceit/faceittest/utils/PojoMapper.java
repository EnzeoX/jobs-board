package com.faceit.faceittest.utils;

import com.faceit.faceittest.entity.*;
import com.faceit.faceittest.models.Job;
import com.faceit.faceittest.models.JobsData;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikolay Boyko
 */

@Slf4j
public class PojoMapper {

    public static List<JobEntity> jobDataToJobEntities(JobsData data) {
        if (data == null) {
            return new ArrayList<>();
        }

//        // try with streams
//        return data.getData().stream()
//                .map(job -> {
//                    JobEntity jobEntity = new JobEntity();
//                    jobEntity.setSlug(job.getSlug());
//                    jobEntity.setTitle(jobEntity.getTitle());
//                    jobEntity.setDescription(job.getDescription());
//                    jobEntity.setRemote(job.isRemote());
//                    jobEntity.setUrl(job.getUrl());
//                    jobEntity.setCreatedAt(job.getCreatedAt());
//
//                    JobCompanyEntity jobCompanyEntity = new JobCompanyEntity();
//                    jobCompanyEntity.setCompanyName(job.getCompanyName());
//                    jobEntity.setCompany(jobCompanyEntity);
//
//                    JobLocationEntity jobLocationEntity = new JobLocationEntity();
//                    jobLocationEntity.setLocation(job.getLocation());
//                    jobEntity.setLocation(jobLocationEntity);
//
//                    jobEntity.setTypes(job.getJobTypes().stream()
//                            .map(type -> {
//                                JobTypeEntity typeEntity = new JobTypeEntity();
//                                typeEntity.setType(type);
//                                return typeEntity;
//                            })
//                            .toList());
//
//                    jobEntity.setTags(job.getJobTags().stream()
//                            .map(tags -> {
//                                JobTagEntity tagEntity = new JobTagEntity();
//                                tagEntity.setTag(tags);
//                                return tagEntity;
//                            })
//                            .toList());
//
//                    return jobEntity;
//                }).toList();


        List<JobEntity> jobEntities = new ArrayList<>();
        for (Job job : data.getData()) {
            if (job == null) {
                log.warn("There is a null job data");
                continue;
            }
            JobEntity jobEntity = new JobEntity();
            jobEntities.add(jobEntity);

            jobEntity.setSlug(job.getSlug());
            jobEntity.setTitle(jobEntity.getTitle());
            jobEntity.setDescription(job.getDescription());
            jobEntity.setRemote(job.isRemote());
            jobEntity.setUrl(job.getUrl());
            jobEntity.setCreatedAt(job.getCreatedAt());

            // Set company
            JobCompanyEntity jobCompanyEntity = new JobCompanyEntity();
            jobCompanyEntity.setCompanyName(job.getCompanyName());
            jobEntity.setCompany(jobCompanyEntity);

            // Set location
            JobLocationEntity jobLocationEntity = new JobLocationEntity();
            jobLocationEntity.setLocation(job.getLocation());
            jobEntity.setLocation(jobLocationEntity);

            // Set tags
            List<JobTagEntity> jobTagEntityList = new ArrayList<>();
            for (String jobTag : job.getJobTags()) {
                JobTagEntity jobTagEntity = new JobTagEntity();
                jobTagEntity.setTag(jobTag);
                jobTagEntityList.add(jobTagEntity);
            }
            jobEntity.setTags(jobTagEntityList);

            // Set types
            List<JobTypeEntity> jobTypeEntitiesList = new ArrayList<>();
            for (String jobType : job.getJobTypes()) {
                JobTypeEntity jobTypeEntity = new JobTypeEntity();
                jobTypeEntity.setType(jobType);
                jobTypeEntitiesList.add(jobTypeEntity);
            }
            jobEntity.setTypes(jobTypeEntitiesList);
        }
        return jobEntities;
    }
}

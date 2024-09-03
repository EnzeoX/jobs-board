package com.jobs.simplistic.service;

import com.jobs.simplistic.dto.ProjectionCityStatistics;
import com.jobs.simplistic.entity.*;
import com.jobs.simplistic.models.Job;
import com.jobs.simplistic.models.JobsData;
import com.jobs.simplistic.repository.*;
import com.jobs.simplistic.utils.PojoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Nikolay Boyko
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class JobService {

    private final JobLocationRepository jobLocationRepository;
    private final JobCompanyRepository jobCompanyRepository;
    private final JobTypeRepository jobTypeRepository;
    private final JobTagRepository jobTagRepository;
    private final JobRepository jobRepository;

    // Not save to do, probably will poorly perform with high count of data
    public List<Job> getAllJobs() {
        List<JobEntity> jobEntities = jobRepository.findAll();
        if (jobEntities.size() == 0) return new ArrayList<>();
        return jobEntities.stream()
                .map(PojoMapper::jobEntityToJob)
                .toList();
    }

    public Page<Job> getPaginated(Pageable pageable) {
        Page<JobEntity> entities = jobRepository.findAll(pageable);
        return entities.map(PojoMapper::jobEntityToJob);
    }

    public List<ProjectionCityStatistics> getStatisticsByCity(String direction) {
        return direction.equals("ASC") ? jobRepository.getStatisticsByCityAsc()
                : jobRepository.getStatisticsByCityDesc();
    }

    public void saveAll(List<JobEntity> jobs) {
        jobs.forEach(this::save);
    }

    @Transactional
    public void processCollectedListOfData(List<JobsData> dataList) {
        dataList.forEach(this::processCollectedData);
    }

    @Transactional
    public void processCollectedData(JobsData data) {
        if (data == null) throw new NullPointerException("Provided data is null!");
        List<JobEntity> list = PojoMapper.jobDataToJobEntities(data);
        if (list.isEmpty()) {
            log.warn("List is null or empty, nothing to save in DB");
            return;
        }
        saveAll(list);
    }

    @Transactional
    public void save(JobEntity job) {
        if (job == null) return;

        // Check if JobEntity exists in DB
        if (jobRepository.findJobEntityBySlug(job.getSlug()) != null) {
            return;
        }

        JobCompanyEntity company = job.getCompany();
        JobCompanyEntity existingCompany = jobCompanyRepository.findByCompanyName(company.getCompanyName());
        job.setCompany(
                Objects.requireNonNullElseGet(existingCompany,
                        () -> jobCompanyRepository.save(company))
        );

        JobLocationEntity location = job.getLocation();
        JobLocationEntity existingLocation = jobLocationRepository.findByLocationName(location.getLocationName());
        job.setLocation(
                Objects.requireNonNullElseGet(existingLocation,
                        () -> jobLocationRepository.save(location))
        );

        Set<JobTagEntity> tags = job.getTags();
        Set<JobTagEntity> savedTags = new HashSet<>();
        for (JobTagEntity tag : tags) {
            savedTags.add(Objects.requireNonNullElseGet(jobTagRepository.findByTagName(tag.getTagName()),
                    () -> jobTagRepository.save(tag)));
        }
        job.setTags(savedTags);

        Set<JobTypeEntity> types = job.getTypes();
        Set<JobTypeEntity> savedTypes = new HashSet<>();
        for (JobTypeEntity type : types) {
            savedTypes.add(Objects.requireNonNullElseGet(jobTypeRepository.findByTypeName(type.getTypeName()),
                    () -> jobTypeRepository.save(type)));
        }
        job.setTypes(savedTypes);

        jobRepository.save(job);
    }

    @Transactional
    public int deleteOlderThan(int olderThan) {
        return jobRepository.deleteOlderThanDate(new Date(new Date().getTime() - olderThan * 24 * 60 * 60 * 1000L));
    }
}

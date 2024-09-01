package com.faceit.faceittest.service;

import com.faceit.faceittest.entity.*;
import com.faceit.faceittest.models.JobsData;
import com.faceit.faceittest.repository.*;
import com.faceit.faceittest.utils.PojoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Nikolay Boyko
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class JobService {

    //    @PostConstruct
    private void initial() {
        List<JobEntity> list = new ArrayList<>();
        JobEntity jobEntity = new JobEntity();
        list.add(jobEntity);
        jobEntity.setSlug("teamleiter-brandschutz-und-sicherheitstechnik-in-lubeck-381773");
        jobEntity.setCreatedAt(new Date(1725007744));
        jobEntity.setDescription("\u003Cp\u003E\u003Cstrong\u003EUnser Kunde ist ein Dienstleistungsunternehmen für das übergreifende kaufmännische, technische und infrastrukturelle Management von Gebäuden. Für das Team in Lübeck suchen wir zur Direktvermittlung in Vollzeit (Festanstellung) einen Teamleiter (w/m/d) Brandschutz- und Sicherheittechnik.\u003C/strong\u003E\u003C/p\u003E\n\u003Ch2\u003EAufgaben\u003C/h2\u003E\n\u003Cul\u003E\n\u003Cli\u003EAufbau, Strukturierung und Verantwortung für ein neues Team im Bereich Brandschutz und Sicherheitstechnik\u003C/li\u003E\n\u003Cli\u003EPersonaleinsatzplanung\u003C/li\u003E\n\u003Cli\u003EZusammenarbeit sowie Abstimmung mit den anderen Teams aus dem handwerklichen Bereich\u003C/li\u003E\n\u003Cli\u003EÜberwachung der Einhaltung behördlicher Auflagen und gesetzlicher Bestimmungen\u003C/li\u003E\n\u003Cli\u003EAuswahl und Koordination der Fremdfirmen nach Einholung und Bewertung von Angeboten\u003C/li\u003E\n\u003Cli\u003EErstellung der Jahresinstandhaltungspläne sowie Abstimmung mit den Entscheidungsträgern\u003C/li\u003E\n\u003Cli\u003EUnterstützung des Einkaufs bei Wartungsverträgen, Standardisierung von Ersatzteilen, etc.\u003C/li\u003E\n\u003Cli\u003EKoordination der erforderlichen Hilfs- und Betriebsstoffe, Ersatzteile und Verbrauchsmaterialien\u003C/li\u003E\n\u003Cli\u003EErfassung der dem Team zugeordneten Anlagen und Geräte als Vorbereitung zur Einspielung SAP\u003C/li\u003E\n\u003C/ul\u003E\n\u003Ch2\u003EQualifikation\u003C/h2\u003E\n\u003Cul\u003E\n\u003Cli\u003EMeister im Bereich Elektrotechnik\u003C/li\u003E\n\u003Cli\u003EErfahrung in der Personalführung\u003C/li\u003E\n\u003Cli\u003ESozial- und Dienstleistungskompetenz\u003C/li\u003E\n\u003Cli\u003Ekommunikationsstark und verantwortungsbewusste Arbeitsweise\u003C/li\u003E\n\u003Cli\u003Esehr gute MS-Office Kenntnisse\u003C/li\u003E\n\u003Cli\u003ESAP-Kenntnisse wünschenswert\u003C/li\u003E\n\u003Cli\u003EKenntnisse in BMA, BOS, EMA und GLT\u003C/li\u003E\n\u003C/ul\u003E\n\u003Ch2\u003EBenefits\u003C/h2\u003E\n\u003Cul\u003E\n\u003Cli\u003Eunbefristeter, krisensicherer Festanstellung (langfristige Kunden-Verträge)\u003C/li\u003E\n\u003Cli\u003E30 Tage Urlaub plus den 24. und 31.12. als arbeitsfreie Tage\u003C/li\u003E\n\u003Cli\u003Ezahlreiche Förder- und Weiterbildungsmöglichkeiten\u003C/li\u003E\n\u003Cli\u003EZuschuss zur betrieblichen Altersvorsorge\u003C/li\u003E\n\u003Cli\u003ELeasing eines „JobRads“ möglich\u003C/li\u003E\n\u003Cli\u003Evergünstigtes „Jobticket“ zur Nutzung des ÖPNV\u003C/li\u003E\n\u003Cli\u003Eflexible und familienfreundliche Arbeitszeiten durch Gleitzeit-Modell und eine konsequente Zeiterfassung\u003C/li\u003E\n\u003C/ul\u003E\n\u003Cp\u003E\u003Cstrong\u003EHabe ich Ihr Interesse geweckt?\u003C/strong\u003E\u003C/p\u003E\n\u003Cp\u003EDann freue ich mich auf Ihre Nachricht oder rufen Sie mich unverbindlich an (0151 5711 0633). \u003C/p\u003E\n\u003Cp\u003ESelbstverständlich behandle ich Ihre Kontaktaufnahme vertraulich. Mein Team und ich begleiten und beraten Sie gerne im gesamten Bewerbungsprozess.\u003C/p\u003E\n\u003Cp\u003EFind more \u003Ca href=\"https://www.arbeitnow.com/english-speaking-jobs\"\u003EEnglish Speaking Jobs in Germany\u003C/a\u003E on Arbeitnow\u003C/a\u003E");
        jobEntity.setRemote(false);
        jobEntity.setTitle("Teamleiter (w/m/d) Brandschutz- und Sicherheitstechnik in Lübeck");
        jobEntity.setUrl("https://www.arbeitnow.com/jobs/companies/forty-five-personalberatung-wiesbaden-gmbh-co-kg/teamleiter-brandschutz-und-sicherheitstechnik-in-lubeck-381773");

        JobCompanyEntity jobCompanyEntity = JobCompanyEntity.builder()
                .companyName("forty-five Personalberatung Wiesbaden GmbH & Co. KG")
                .build();
//        jobEntity.setCompany(jobCompanyEntity);

        List<JobTagEntity> jobTagEntityList = List.of(JobTagEntity.builder()
                .tagName("Electrical Engineering")
                .build());
//        jobEntity.setTags(jobTagEntityList);

        JobLocationEntity jobLocationEntity = JobLocationEntity.builder()
                .locationName("Lübeck")
                .build();
//        jobEntity.setLocation(jobLocationEntity);
        saveAll(list);
    }

    private final JobLocationRepository jobLocationRepository;
    private final JobCompanyRepository jobCompanyRepository;
    private final JobTypeRepository jobTypeRepository;
    private final JobTagRepository jobTagRepository;
    private final JobRepository jobRepository;

    private final ObjectMapper mapper;

    public List<JobEntity> getAllJobs() {
        return jobRepository.findAll();
    }

    public Page<JobEntity> getAllPaginated(Pageable pageable) {
        return jobRepository.findAll(pageable);
    }

    public void saveAll(List<JobEntity> jobs) {
        jobRepository.saveAll(jobs);
    }

    public void processAndSave(String jsonData) {
        try {
            processCollectedData(mapper.readValue(jsonData, JobsData.class));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Transactional
    public void processCollectedData(JobsData data) {
        if (data == null) throw new NullPointerException("Provided data is null!");
        List<JobEntity> list = PojoMapper.jobDataToJobEntities(data);
        if (list.isEmpty()) {
            log.warn("List is null or empty, nothing to save in DB");
            return;
        }
        for (JobEntity job : list) {
            if (job == null) return;

            JobCompanyEntity company = job.getCompany();
            JobCompanyEntity existingCompany = jobCompanyRepository.findByCompanyName(company.getCompanyName());
            if (existingCompany != null) {
                job.setCompany(existingCompany);
            } else {
                job.setCompany(jobCompanyRepository.save(company));
            }

            JobLocationEntity location = job.getLocation();
            JobLocationEntity existingLocation = jobLocationRepository.findByLocationName(location.getLocationName());
            if (existingLocation != null) {
                job.setLocation(existingLocation);
            } else {
                job.setLocation(jobLocationRepository.save(location));
            }

            Set<JobTagEntity> tags = job.getTags();
            Set<JobTagEntity> savedTags = new HashSet<>();
            for (JobTagEntity tag : tags) {
                if (tag.getTagName().equals("Remote")) {
                    log.info("Remote tag");
                }
                JobTagEntity existingTag = jobTagRepository.findByTagName(tag.getTagName());
                if (existingTag == null) {
                    try {
                        savedTags.add(jobTagRepository.save(tag));
                    } catch (DataIntegrityViolationException e) {
                        // Handle unique constraint violation
                        existingTag = jobTagRepository.findByTagName(tag.getTagName());
                        if (existingTag != null) {
                            savedTags.add(existingTag);
                        } else {
                            throw e; // Re-throw if the error is not due to duplication
                        }
                    }
                } else {
                    existingTag.getJobs().add(job);
                }
                savedTags.add(existingTag);
            }
            job.setTags(savedTags);

            Set<JobTypeEntity> types = job.getTypes();
            Set<JobTypeEntity> savedTypes = new HashSet<>();
            for (JobTypeEntity type : types) {
                JobTypeEntity existingType = jobTypeRepository.findByTypeName(type.getTypeName());
                if (existingType != null) {
                    existingType.getJobs().add(job);
                } else {
                    existingType = jobTypeRepository.save(type);
                }
                savedTypes.add(existingType);
            }
            job.setTypes(savedTypes);

            jobRepository.save(job);
        }
    }
}

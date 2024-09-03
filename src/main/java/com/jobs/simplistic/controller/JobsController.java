package com.jobs.simplistic.controller;

import com.jobs.simplistic.dto.ProjectionCityStatistics;
import com.jobs.simplistic.entity.JobCompanyEntity;
import com.jobs.simplistic.entity.JobEntity;
import com.jobs.simplistic.entity.JobLocationEntity;
import com.jobs.simplistic.entity.JobTagEntity;
import com.jobs.simplistic.models.Job;
import com.jobs.simplistic.models.ResponseWrapper;
import com.jobs.simplistic.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author Nikolay Boyko
 */

@Slf4j
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class JobsController {

    private final JobService jobService;

//    @GetMapping("save-dump")
//    public void saveDump() {
//        JobEntity jobEntity = new JobEntity();
//        jobEntity.setSlug("teamleiter-brandschutz-und-sicherheitstechnik-in-lubeck-381773");
//        jobEntity.setCreatedAt(new Date(1725007744));
//        jobEntity.setDescription("\u003Cp\u003E\u003Cstrong\u003EUnser Kunde ist ein Dienstleistungsunternehmen für das übergreifende kaufmännische, technische und infrastrukturelle Management von Gebäuden. Für das Team in Lübeck suchen wir zur Direktvermittlung in Vollzeit (Festanstellung) einen Teamleiter (w/m/d) Brandschutz- und Sicherheittechnik.\u003C/strong\u003E\u003C/p\u003E\n\u003Ch2\u003EAufgaben\u003C/h2\u003E\n\u003Cul\u003E\n\u003Cli\u003EAufbau, Strukturierung und Verantwortung für ein neues Team im Bereich Brandschutz und Sicherheitstechnik\u003C/li\u003E\n\u003Cli\u003EPersonaleinsatzplanung\u003C/li\u003E\n\u003Cli\u003EZusammenarbeit sowie Abstimmung mit den anderen Teams aus dem handwerklichen Bereich\u003C/li\u003E\n\u003Cli\u003EÜberwachung der Einhaltung behördlicher Auflagen und gesetzlicher Bestimmungen\u003C/li\u003E\n\u003Cli\u003EAuswahl und Koordination der Fremdfirmen nach Einholung und Bewertung von Angeboten\u003C/li\u003E\n\u003Cli\u003EErstellung der Jahresinstandhaltungspläne sowie Abstimmung mit den Entscheidungsträgern\u003C/li\u003E\n\u003Cli\u003EUnterstützung des Einkaufs bei Wartungsverträgen, Standardisierung von Ersatzteilen, etc.\u003C/li\u003E\n\u003Cli\u003EKoordination der erforderlichen Hilfs- und Betriebsstoffe, Ersatzteile und Verbrauchsmaterialien\u003C/li\u003E\n\u003Cli\u003EErfassung der dem Team zugeordneten Anlagen und Geräte als Vorbereitung zur Einspielung SAP\u003C/li\u003E\n\u003C/ul\u003E\n\u003Ch2\u003EQualifikation\u003C/h2\u003E\n\u003Cul\u003E\n\u003Cli\u003EMeister im Bereich Elektrotechnik\u003C/li\u003E\n\u003Cli\u003EErfahrung in der Personalführung\u003C/li\u003E\n\u003Cli\u003ESozial- und Dienstleistungskompetenz\u003C/li\u003E\n\u003Cli\u003Ekommunikationsstark und verantwortungsbewusste Arbeitsweise\u003C/li\u003E\n\u003Cli\u003Esehr gute MS-Office Kenntnisse\u003C/li\u003E\n\u003Cli\u003ESAP-Kenntnisse wünschenswert\u003C/li\u003E\n\u003Cli\u003EKenntnisse in BMA, BOS, EMA und GLT\u003C/li\u003E\n\u003C/ul\u003E\n\u003Ch2\u003EBenefits\u003C/h2\u003E\n\u003Cul\u003E\n\u003Cli\u003Eunbefristeter, krisensicherer Festanstellung (langfristige Kunden-Verträge)\u003C/li\u003E\n\u003Cli\u003E30 Tage Urlaub plus den 24. und 31.12. als arbeitsfreie Tage\u003C/li\u003E\n\u003Cli\u003Ezahlreiche Förder- und Weiterbildungsmöglichkeiten\u003C/li\u003E\n\u003Cli\u003EZuschuss zur betrieblichen Altersvorsorge\u003C/li\u003E\n\u003Cli\u003ELeasing eines „JobRads“ möglich\u003C/li\u003E\n\u003Cli\u003Evergünstigtes „Jobticket“ zur Nutzung des ÖPNV\u003C/li\u003E\n\u003Cli\u003Eflexible und familienfreundliche Arbeitszeiten durch Gleitzeit-Modell und eine konsequente Zeiterfassung\u003C/li\u003E\n\u003C/ul\u003E\n\u003Cp\u003E\u003Cstrong\u003EHabe ich Ihr Interesse geweckt?\u003C/strong\u003E\u003C/p\u003E\n\u003Cp\u003EDann freue ich mich auf Ihre Nachricht oder rufen Sie mich unverbindlich an (0151 5711 0633). \u003C/p\u003E\n\u003Cp\u003ESelbstverständlich behandle ich Ihre Kontaktaufnahme vertraulich. Mein Team und ich begleiten und beraten Sie gerne im gesamten Bewerbungsprozess.\u003C/p\u003E\n\u003Cp\u003EFind more \u003Ca href=\"https://www.arbeitnow.com/english-speaking-jobs\"\u003EEnglish Speaking Jobs in Germany\u003C/a\u003E on Arbeitnow\u003C/a\u003E");
//        jobEntity.setRemote(false);
//        jobEntity.setTitle("Teamleiter (w/m/d) Brandschutz- und Sicherheitstechnik in Lübeck");
//        jobEntity.setUrl("https://www.arbeitnow.com/jobs/companies/forty-five-personalberatung-wiesbaden-gmbh-co-kg/teamleiter-brandschutz-und-sicherheitstechnik-in-lubeck-381773");
//        jobEntity.setAddedAt(new Date(new Date().getTime() - 40 * 24 * 3600 * 1000L));
//        JobCompanyEntity jobCompanyEntity = JobCompanyEntity.builder()
//                .companyName("forty-five Personalberatung Wiesbaden GmbH & Co. KG")
//                .build();
//        ;
//        jobEntity.setCompany(jobCompanyEntity);
//
//        List<JobTagEntity> jobTagEntityList = List.of(JobTagEntity.builder()
//                .tagName("Electrical Engineering")
//                .build());
//        Set<JobTagEntity> set = new HashSet<>(jobTagEntityList);
//        jobEntity.setTags(set);
//
//
//        JobLocationEntity jobLocationEntity = JobLocationEntity.builder()
//                .locationName("Lübeck")
//                .build();
//        jobEntity.setLocation(jobLocationEntity);
//
//        jobService.save(jobEntity);
//
//    }

    @GetMapping("")
    public ResponseEntity<String> simpleResponse() {
        return ResponseEntity.status(HttpStatus.OK).body("{\"status\":\"OK\"}");
    }

    @GetMapping(path = "/all-jobs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Job>> getAllJobs() {
        ResponseWrapper<Job> wrapper = new ResponseWrapper<>(jobService.getAllJobs());
        return ResponseEntity.status(HttpStatus.OK).body(wrapper);
    }

    @GetMapping(path = "/jobs")
    public ResponseEntity<ResponseWrapper<Job>> getPaginatedJobs(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam(value = "order", defaultValue = "ASC") String order,
            @RequestParam(value = "sort", defaultValue = "slug") Optional<String[]> sort) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(20);
        String[] sortArray = sort.orElse(new String[]{"title"});
        Page<Job> paginatedJobs = jobService
                .getPaginated(PageRequest
                        .of(currentPage - 1, pageSize, Sort.by(Sort.Direction.valueOf(order), sortArray)));
        ResponseWrapper<Job> wrapper = new ResponseWrapper<>(paginatedJobs.getContent());
        return ResponseEntity.status(HttpStatus.OK).body(wrapper);
    }

    @GetMapping(path = "statistics")
    public ResponseEntity<List<ProjectionCityStatistics>> getStatistics(
            @RequestParam("order") Optional<String> order) {
        String directionValue = order.orElse("ASC");
        return ResponseEntity.status(HttpStatus.OK).body(
                jobService.getStatisticsByCity(directionValue)
        );
    }
}

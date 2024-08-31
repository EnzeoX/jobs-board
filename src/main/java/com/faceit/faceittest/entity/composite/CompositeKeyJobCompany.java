package com.faceit.faceittest.entity.composite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Nikolay Boyko
 */

@Getter
@Setter
@Embeddable
public class CompositeKeyJobCompany {

    @Column(name = "job_id")
    private int jobId;

    @Column(name = "company_id")
    private int companyId;
}

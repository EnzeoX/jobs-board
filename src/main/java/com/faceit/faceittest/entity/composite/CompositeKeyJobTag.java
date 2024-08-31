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
public class CompositeKeyJobTag {

    @Column(name = "job_id")
    private int jobId;

    @Column(name = "tag_id")
    private int tagId;
}

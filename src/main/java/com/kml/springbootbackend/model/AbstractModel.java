package com.kml.springbootbackend.model;

import java.io.Serializable;
import java.time.Instant;

import lombok.Data;

@Data
public abstract class AbstractModel implements Serializable, Cloneable {
    
    private static final long serialVersionUID = 1L;

    protected String createdBy;

    protected Instant dateCreated;

    protected String modifiedBy;

    protected Instant dateModified;

    protected String status;
}

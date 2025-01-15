package com.kml.springbootbackend.dto;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractDTO {
    
    String createdBy;

    Instant dateCreated;

    String modifiedBy;

    Instant dateModified;

    String status;
}

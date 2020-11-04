package com.equilibrium.webapp.resource;

import com.equilibrium.webapp.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RateResource extends AuditModel {

    private Long id;
    private Float value;
    private Integer period;
    private String type;
    private Integer capitalization;
    private Double realRate;
}

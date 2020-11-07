package com.equilibrium.webapp.resource;

import com.equilibrium.webapp.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovementResource extends AuditModel {
    private Long id;
    private String description;
    private Float amount;
}

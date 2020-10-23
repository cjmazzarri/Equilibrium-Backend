package com.equilibrium.webapp.resource;

import com.equilibrium.webapp.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MaintenanceFeeResource extends AuditModel {
    private Long id;
    private String period;
    private Float value;
}

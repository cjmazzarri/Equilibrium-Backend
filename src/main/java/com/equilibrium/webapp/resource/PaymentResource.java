package com.equilibrium.webapp.resource;

import com.equilibrium.webapp.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResource extends AuditModel {
    private Long id;
    private String description;
    private Float amount;
}

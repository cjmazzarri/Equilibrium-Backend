package com.equilibrium.webapp.resource;

import com.equilibrium.webapp.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryFeeResource extends AuditModel {
    private Long id;
    private Float value;
    private int frequency;
    private String type;
}

package com.equilibrium.webapp.resource;

import com.equilibrium.webapp.domain.model.AuditModel;
import com.equilibrium.webapp.domain.model.Rate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientResource extends AuditModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String currency;
    private Float creditAmount;
    private RateResource rate;
    private MaintenanceFeeResource maintenanceFee;
    private DeliveryFeeResource deliveryFee;
}

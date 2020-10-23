package com.equilibrium.webapp.resource;

import com.equilibrium.webapp.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ClientResource extends AuditModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String currency;
}

package com.equilibrium.webapp.resource;

import com.equilibrium.webapp.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommerceResource extends AuditModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private Date birthDate;
}

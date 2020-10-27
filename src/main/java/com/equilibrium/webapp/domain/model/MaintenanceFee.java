package com.equilibrium.webapp.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "maintenance_fees")
public class MaintenanceFee extends AuditModel{

    @Id
    private Long id;

    @NotNull
    private String period;

    @NotNull
    private Float value;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "id")
    private Client client;
}

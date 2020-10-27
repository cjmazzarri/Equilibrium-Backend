package com.equilibrium.webapp.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "rates")
@Getter
@Setter
public class Rate extends AuditModel{

    @Id
    private Long id;

    @NotNull
    private Float value;

    @NotNull
    private String period;

    @NotNull
    private String type;

    @NotNull
    private String capitalization;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "id")
    private Client client;
}

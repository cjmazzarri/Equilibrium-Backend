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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Float value;

    @NotNull
    private String period;

    @NotNull
    private String type;

    @OneToOne(mappedBy = "rates")
    private Client client;
}

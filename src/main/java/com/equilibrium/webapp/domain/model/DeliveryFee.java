package com.equilibrium.webapp.domain.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "delivery_fees")
public class DeliveryFee extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Float value;

    @NotNull
    private int frequency;

    @NotNull
    private String type;

    @OneToOne(mappedBy = "delivery_fee")
    private Client client;
}

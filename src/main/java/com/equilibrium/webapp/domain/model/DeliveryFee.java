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
    @Column(name = "client_id")
    private Long id;

    @NotNull
    private Float value;

    @NotNull
    private int frequency;

    @NotNull
    private String type;

    @OneToOne
    @MapsId
    @JoinColumn(name = "client_id")
    private Client client;
}

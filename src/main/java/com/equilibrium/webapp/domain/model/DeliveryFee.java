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
    private Long id;

    @NotNull
    private Float value;

    @NotNull
    private Integer frequency;

    @NotNull
    private String type;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "id")
    private Client client;
}

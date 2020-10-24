package com.equilibrium.webapp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "commerce_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Commerce commerce;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String currency;

    @NotNull
    @JsonIgnore
    private Float creditAmount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rate_id", referencedColumnName = "id")
    @JsonIgnore
    private Rate rate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_fee_id", referencedColumnName = "id")
    @JsonIgnore
    private DeliveryFee deliveryFee;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private MaintenanceFee maintenanceFee;
}

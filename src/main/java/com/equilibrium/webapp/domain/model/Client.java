package com.equilibrium.webapp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.lang.Math;

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
    
    private Float creditAmount;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private Rate rate;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private DeliveryFee deliveryFee;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private MaintenanceFee maintenanceFee;

    private Integer activeDays=0;

    public void nextDay(){
        this.activeDays++;
        if(this.getDeliveryFee().getType().equals("Periodo")){
            if (feeTrigger(this.getDeliveryFee().getFrequency())) this.creditAmount+=this.getDeliveryFee().getValue();
        }
        switch (this.getMaintenanceFee().getPeriod()){
            case "s":
                if (feeTrigger(7)) this.creditAmount+=this.getMaintenanceFee().getValue();
                break;
            case "q":
                if (feeTrigger(15)) this.creditAmount+=this.getMaintenanceFee().getValue();
                break;
            case "m":
                if (feeTrigger(30)) this.creditAmount+=this.getMaintenanceFee().getValue();
                break;
        }
        this.creditAmount=(float)(Math.round((this.creditAmount*(1d+this.rate.getRealRate()) * 100.0)) / 100.0);
    }

    private boolean feeTrigger(Integer frequency) {
        return this.activeDays % frequency == 0 && this.activeDays != 0;
    }
}

package com.equilibrium.webapp.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.Math;

@Entity
@Table(name = "rates")
@Getter
@Setter
public class Rate extends AuditModel{

    @Id
    private Long id;

    @NotNull
    private Float value;

    private Integer period;

    @NotNull
    private String type;

    @NotNull
    private Integer capitalization;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "id")
    private Client client;

    public Boolean isSimple(){
        if(this.type.equals("simple"))
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    private Double realRate;

    public void setRealRate(){
        if(this.isSimple())
            this.realRate=(double)((this.value/100)/this.period);
        else if(this.type.equals("nominal")){
            Double m=((double)this.period/(double)this.capitalization);
            Double n=(1/(double)this.capitalization);
            this.realRate=Math.pow(1+((double)this.value/100)/m, n)-1;
        }
        else if(this.type.equals("efectiva")){
            this.realRate=(Math.pow(1+(this.value/100), 1.0/this.period)-1);
        }
    }
}

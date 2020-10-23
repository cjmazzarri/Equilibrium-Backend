package com.equilibrium.webapp.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SavePaymentResource {

    @NotNull
    @Lob
    private String description;

    @NotNull
    private Float amount;
}

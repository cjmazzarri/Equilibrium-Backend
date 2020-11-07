package com.equilibrium.webapp.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaveMovementResource {

    @NotNull
    @Lob
    private String description;

    @NotNull
    private Float amount;
}

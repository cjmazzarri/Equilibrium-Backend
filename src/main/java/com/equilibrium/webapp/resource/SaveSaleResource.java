package com.equilibrium.webapp.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaveSaleResource {

    @NotNull
    @Lob
    private String description;

    @NotNull
    @Min(value = 0, message = "Sale amount must be positive.")
    private Float amount;
}

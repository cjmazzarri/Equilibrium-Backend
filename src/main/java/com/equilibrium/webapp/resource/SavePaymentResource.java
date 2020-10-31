package com.equilibrium.webapp.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SavePaymentResource {

    @NotNull
    @Lob
    private String description;

    @NotNull
    @Min(value = 0, message = "Payment amount must be positive.")
    private Float amount;
}

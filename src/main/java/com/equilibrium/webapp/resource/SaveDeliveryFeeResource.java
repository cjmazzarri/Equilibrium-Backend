package com.equilibrium.webapp.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveDeliveryFeeResource {
    @NotNull
    @Min(value = 0, message = "Delivery Fee value must be positive.")
    private Float value;

    @NotNull
    private Integer frequency;

    @NotNull
    @Size(max = 7)
    private String type;
}

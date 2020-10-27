package com.equilibrium.webapp.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveDeliveryFeeResource {
    @NotNull
    private Float value;

    @NotNull
    private Integer frequency;

    @NotBlank
    @NotNull
    @Size(max = 7)
    private String type;
}

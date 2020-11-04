package com.equilibrium.webapp.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveRateResource {
    @NotNull
    @Min(value = 0, message = "Rate value must be positive.")
    private Float value;

    @NotNull
    private Integer period;

    @NotBlank
    @NotNull
    @Size(max = 8)
    private String type;

    @NotNull
    private Integer capitalization;
}

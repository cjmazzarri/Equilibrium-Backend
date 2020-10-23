package com.equilibrium.webapp.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveRateResource {
    @NotNull
    private Float value;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String period;

    @NotBlank
    @NotNull
    @Size(max = 8)
    private String type;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String capitalization;
}

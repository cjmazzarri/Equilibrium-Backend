package com.equilibrium.webapp.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveMaintenanceFeeResource {
    @NotBlank
    @NotNull
    @Size(max = 1)
    private String period;

    @NotNull
    private Float value;
}

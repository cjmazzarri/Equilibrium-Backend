package com.equilibrium.webapp.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveMaintenanceFeeResource {
    @NotNull
    @Size(max = 1)
    private String period;

    @NotNull
    @Min(value = 0, message = "Maintenance Fee value must be positive.")
    private Float value;
}

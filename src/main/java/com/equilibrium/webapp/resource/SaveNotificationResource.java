package com.equilibrium.webapp.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaveNotificationResource {
    @NotNull
    @Lob
    private String content;
}

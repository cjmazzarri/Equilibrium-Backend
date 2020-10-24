package com.equilibrium.webapp.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Getter
@Setter
public class SaveCommerceResource {

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String lastName;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String username;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String password;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String emailAddress;

    @NotNull
    private Date birthDate;

}

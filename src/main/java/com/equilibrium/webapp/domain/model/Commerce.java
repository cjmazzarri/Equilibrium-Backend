package com.equilibrium.webapp.domain.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "commerces")
public class Commerce extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commerce_id;

    @NotNull
    private String first_name;

    @NotNull
    private String last_name;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @Column(unique = true)
    private String email_address;

    @NotNull
    private Date birth_date;
}

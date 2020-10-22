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
    String first_name;

    @NotNull
    String last_name;

    @NotNull
    @Column(unique = true)
    String username;

    @NotNull
    String password;

    @Column(unique = true)
    String email_address;

    @NotNull
    Date birth_date;
}

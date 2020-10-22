package com.equilibrium.webapp.domain.model;


import lombok.Data;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "commerces")
public class Commerce extends AuditingEntityListener {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commerce_id;

    @NotNull
    @NotBlank
    String first_name;

    
    String last_name;

    String username;

    String password;

    String email_address;

    Date birth_date;
}

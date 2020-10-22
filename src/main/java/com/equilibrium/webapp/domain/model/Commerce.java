package com.equilibrium.webapp.domain.model;

import lombok.Data;

import java.util.Date;

@Data
public class Commerce {


    int commerce_id;

    String first_name;

    String last_name;

    String username;

    String password;

    String email_address;

    Date birth_date;
}

package com.esuvorov.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "BIRTHDATE")
    private Long birthDate;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "EMAIL")
    private String email;
}

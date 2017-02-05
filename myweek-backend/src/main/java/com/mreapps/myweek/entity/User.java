package com.mreapps.myweek.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "\"user\"")
public class User extends AbstractEntity
{
    @Column(name = "email_address", nullable = false, unique = true)
    private String emailAddress;

    @JsonIgnore
    @Column(name = "encrypted_password", nullable = false)
    private String encryptedPassword;

    @ManyToOne
    @JoinColumn(name = "person_uid")
    private Person person;
}

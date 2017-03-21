package com.mreapps.myweek.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mreapps.myweek.converter.GenderConverter;
import com.mreapps.myweek.enums.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "person")
public class Person extends AbstractEntity
{
    @Column(name = "firstname", length = 100, nullable = false)
    private String firstname;

    @Column(name = "middlename", length = 100)
    private String middlename;

    @Column(name = "lastname", length = 100, nullable = false)
    private String lastname;

    @Column(name = "gender")
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @Column(name = "birthday")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "CET")
    private Date birthday;

    @ManyToMany
    @JoinTable(
            name = "person_admin",
            joinColumns = @JoinColumn(name = "person_uid", referencedColumnName = "uid"),
            inverseJoinColumns = @JoinColumn(name = "user_uid", referencedColumnName = "uid"))
    private Set<User> admins = new HashSet<>();

}

package com.mreapps.myweek.entity;

import lombok.Data;

import javax.persistence.*;

/**
 *
 */
@Data
@MappedSuperclass
abstract class AbstractEntity implements BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid", nullable = false)
    private Long id;

    @Override
    public Long getId()
    {
        return id;
    }
}

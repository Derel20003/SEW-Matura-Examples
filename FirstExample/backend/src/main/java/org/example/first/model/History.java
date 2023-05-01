package org.example.first.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class History extends PanacheEntity {

    private String name;
    private String description;

}

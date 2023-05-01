package at.htl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Course extends PanacheEntityBase {


    @Id
    public String id;
    public String title;
    public String description;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "prerequisite")
    public Course course;
    // TODO Voraussetzungs-Kurs definieren
}

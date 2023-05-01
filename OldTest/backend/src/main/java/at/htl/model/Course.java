package at.htl.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
public class Course {

    @Id
    @Column(length = 10)
    public String id;
    @Column(nullable = false, length = 50)
    public String title;
    @Column(length = 2000)
    public String description;

    // TODO Voraussetzungs-Kurs definieren
    @ManyToOne
    @JoinColumn(name = "prerequisite")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    public Course prerequisite;

}

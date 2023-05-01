package at.htl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class Course extends PanacheEntityBase {

    @Id
    @Column(length = 10)
    public String id;
    @Column(length = 50, nullable = false)

    public String title;
    @Column(length = 20000)

    public String description;

    @ManyToOne
    @JoinColumn(name = "prerequisite")
    @JsonIgnoreProperties({"prerequisite"})
    public Course prerequisite;

    // TODO Voraussetzungs-Kurs definieren
}

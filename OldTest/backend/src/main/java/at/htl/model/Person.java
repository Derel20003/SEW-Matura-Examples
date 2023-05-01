package at.htl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String firstname;

    public String lastname;

    // TODO evtl. Beziehungen definieren lt. Angabe

    @ManyToMany(mappedBy = "attendants")
    @JsonIgnore
    List<CoursePlan> coursePlans;

    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Person() {

    }
}

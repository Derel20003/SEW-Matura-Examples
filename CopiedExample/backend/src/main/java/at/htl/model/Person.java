package at.htl.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Person extends PanacheEntity {
    public String firstname;

    public String lastname;

    // TODO evtl. Beziehungen definieren lt. Angabe

    @ManyToMany(mappedBy = "personList")
    @JsonIgnore
    public List<CoursePlan> coursePlanList = new LinkedList<>();


    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Person() {
    }
}

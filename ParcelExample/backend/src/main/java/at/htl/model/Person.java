package at.htl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Person extends PanacheEntity {

    public String firstname;

    public String lastname;

    @ManyToMany(mappedBy = "personList")
    @JsonIgnore
    public List<CoursePlan> coursePlanList = new LinkedList<>();

    // TODO evtl. Beziehungen definieren lt. Angabe

    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Person() {
    }
}

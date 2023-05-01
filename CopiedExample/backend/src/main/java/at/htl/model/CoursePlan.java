package at.htl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Entity
public class CoursePlan extends PanacheEntity {

    @Column(name = "startdate")
    public LocalDate start;
    @Column(name = "enddate")
    public LocalDate end;
    @Column(length = 255)
    public String location;

    @ManyToMany
    public List<Person> personList = new LinkedList<>();

    @ManyToOne
    @JsonIgnore
    public Course course;

    @ManyToOne
    @JoinColumn(name = "teacher")
    public Person person;


    // TODO Beziehungen definieren lt. Angabe
}

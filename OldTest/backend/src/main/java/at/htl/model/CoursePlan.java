package at.htl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class CoursePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Column(nullable = false, name = "startdate")
    public LocalDate start;
    @Column(name = "enddate")
    public LocalDate end;
    @Column(length = 255)
    public String location;

    // TODO Beziehungen definieren lt. Angabe
    @ManyToOne
    @JoinColumn(nullable = false, name = "teacher")
    public Person teacher;

    @ManyToMany
    public List<Person> attendants;

    @ManyToOne
    @JsonIgnore
    public Course course;

}

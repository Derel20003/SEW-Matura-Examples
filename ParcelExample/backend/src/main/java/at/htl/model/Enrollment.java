package at.htl.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class Enrollment extends PanacheEntityBase {

    @EmbeddedId
    EnrollmentId en_ID = new EnrollmentId();


    @ManyToOne
    @MapsId("student_id")
    @JoinColumn(name = "student_id")
    public Student student;


    @ManyToOne
    @MapsId("class_ID")
    @JoinColumn(name = "class_ID")
    public SchoolClass scClass;


    public String zweig;


}

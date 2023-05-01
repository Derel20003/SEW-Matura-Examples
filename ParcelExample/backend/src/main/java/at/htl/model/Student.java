package at.htl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Student extends PanacheEntityBase {

    @Id
    @GeneratedValue
    public long student_id;

    public String firstname;
    public String lastname;

    @OneToMany(mappedBy = "student")
    @Cascade(CascadeType.ALL)
    @JsonIgnore
    public List<Enrollment> enrollments = new LinkedList<>();

    public Student() {
    }

    public Student(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}

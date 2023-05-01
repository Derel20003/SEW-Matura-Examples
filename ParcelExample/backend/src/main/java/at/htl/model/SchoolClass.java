package at.htl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

@Entity
public class SchoolClass extends PanacheEntityBase {
    @Id
    @GeneratedValue
    public long class_ID;

    public String title;
    public String description;


    @OneToMany(mappedBy = "student")
    @JsonIgnore
    public List<Enrollment> enrollments = new LinkedList<>();

    public SchoolClass(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public SchoolClass() {
    }
}

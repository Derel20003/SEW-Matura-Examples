package at.htl.repository;

import at.htl.model.SchoolClass;
import at.htl.model.Student;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

@ApplicationScoped
public class StudentRepo implements PanacheRepository<Student> {
    public Student findbyName(String firstname, String lastname) {

        TypedQuery<Student> query = getEntityManager().createQuery("" + "select s from Student s where lower(s.firstname) = lower(:firstname) and lower(s.lastname) = lower(:lastname) ", Student.class);
        query.setParameter("firstname", firstname).setParameter("lastname", lastname);
        return query.getResultStream().findFirst().orElse(null);
    }
}

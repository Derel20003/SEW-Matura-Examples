package at.htl.repository;

import at.htl.DTO.StudentDTO;
import at.htl.model.Enrollment;
import at.htl.model.Student;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class EnrollmentRepo implements PanacheRepository<Enrollment> {
    public List<StudentDTO> findByClass(String name) {

        TypedQuery<StudentDTO> query = getEntityManager().createQuery("" +
                "select new at.htl.DTO.StudentDTO(e.student.firstname, e.student.lastname) from Enrollment e where lower(e.scClass.title) = lower(:name)   ", StudentDTO.class);

        query.setParameter("name", name);
        return query.getResultList();
    }
}

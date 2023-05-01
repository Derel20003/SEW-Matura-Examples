package at.htl.repository;

import at.htl.model.Course;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class CourseRepository implements PanacheRepository<Course> {

    public List<Course> search(String text) {
        // TODO soll alle Kurse zurückliefern, die den übergebenen Suchtext entweder in der ID oder im Kurs-Titel enthalten.
        // Die Position und Groß-/Kleinschreibung spielt dabei keine Rolle
        TypedQuery<Course> query = getEntityManager()
                .createQuery("select c from Course c " +
                        "where upper(id) like upper(:text)" +
                        "or upper(title) like upper(:text) ", Course.class);
        query.setParameter("text", '%'+text+'%');
        return query.getResultList();
    }
}

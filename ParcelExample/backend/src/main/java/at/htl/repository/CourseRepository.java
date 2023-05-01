package at.htl.repository;

import at.htl.model.Course;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class CourseRepository implements PanacheRepositoryBase<Course, String> {

    public List<Course> search(String text) {
        TypedQuery<Course> query = getEntityManager().createQuery("" +
                "select c from Course c where lower(c.id) like lower(:text) or lower(c.title) like lower(:text) ", Course.class);
        query.setParameter("text","%"+text+"%");
        return query.getResultList();
    }
}

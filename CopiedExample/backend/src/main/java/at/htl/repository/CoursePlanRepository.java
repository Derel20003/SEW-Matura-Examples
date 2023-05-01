package at.htl.repository;

import at.htl.model.CoursePlan;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import java.util.List;


@ApplicationScoped
public class CoursePlanRepository implements PanacheRepository<CoursePlan> {

    public List<CoursePlan> findByCourseId(String id) {

        TypedQuery<CoursePlan> query = getEntityManager().createQuery("" +
                "select c from CoursePlan c where lower(c.course.id ) = lower(:id) ", CoursePlan.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}

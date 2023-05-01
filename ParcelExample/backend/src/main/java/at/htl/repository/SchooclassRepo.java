package at.htl.repository;

import at.htl.model.SchoolClass;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

@ApplicationScoped
public class SchooclassRepo implements PanacheRepository<SchoolClass> {
    public SchoolClass findByIdorNull(long classId) {
        TypedQuery<SchoolClass> query = getEntityManager().createQuery("" +
                "select sc from SchoolClass  sc where sc.class_ID = :classid", SchoolClass.class);
        query.setParameter("classid",classId);
        return query.getResultStream().findFirst().orElse(null);
    }
}

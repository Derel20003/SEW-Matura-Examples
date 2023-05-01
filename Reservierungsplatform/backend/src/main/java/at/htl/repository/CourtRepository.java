package at.htl.repository;

import at.htl.model.Court;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CourtRepository implements PanacheRepository<Court> {

    public void update(Court court) {
        getEntityManager().merge(court);
    }

    public List<Court> findCourtsByTypeById(long courttype_id) {
        var query = getEntityManager().createQuery(
                "select distinct c.id, c.location, ct.description " +
                "from Court c " +
                "join CourtType ct on ct.id = c.courtType.id " +
                "where c.courtType.id = :courttype_id " +
                "order by c.location");
        query.setParameter("courttype_id", courttype_id);
        return query.getResultList();
    }

    public List<Court> findCourtsByTypeByDescription(String description) {
        var query = getEntityManager().createQuery(
                "select distinct c.id, c.location, ct.description " +
                "from Court c " +
                "join CourtType ct on ct.id = c.courtType.id " +
                "where c.courtType.description like :description " +
                "order by c.location");
        query.setParameter("description", description);
        return query.getResultList();
    }

}

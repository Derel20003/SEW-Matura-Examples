package at.htl.repository;

import at.htl.model.CourtType;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CourtTypeRepository implements PanacheRepository<CourtType> {

    public void update(CourtType courtType) {
        getEntityManager().merge(courtType);
    }

    public List<CourtType> findAllTypes() {
        var query = getEntityManager().createQuery(
                "select ct.description from CourtType ct");
        return query.getResultList();
    }

}

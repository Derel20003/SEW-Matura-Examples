package at.htl.repository;

import at.htl.model.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {

    public Person findByName(String firstname, String lastname) {

        TypedQuery<Person> query = getEntityManager().createQuery("" +
                "select p from Person p where lower(p.firstname) = lower(:firstname) and lower(p.lastname) = lower(:lastname) ", Person.class);
        query.setParameter("firstname", firstname);
        query.setParameter("lastname", lastname);
        return query.getResultStream().findFirst().orElse(null);
    }
}

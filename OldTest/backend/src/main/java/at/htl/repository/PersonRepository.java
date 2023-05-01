package at.htl.repository;

import at.htl.model.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import java.util.Optional;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {

    public Optional<Person> findByName(String firstname, String lastname) {
        TypedQuery<Person> query = getEntityManager().createQuery("select p from Person p " +
                "where p.firstname like :firstname and p.lastname = :lastname", Person.class);
        query.setParameter("firstname", firstname);
        query.setParameter("lastname", lastname);
        return Optional.ofNullable(query.getSingleResult());
    }

    public Person add(String firstname, String lastname) {
        Person p = new Person(firstname, lastname);
        getEntityManager().persist(p);
        return p;
    }
}

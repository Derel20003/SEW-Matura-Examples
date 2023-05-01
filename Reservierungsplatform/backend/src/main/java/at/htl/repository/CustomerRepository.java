package at.htl.repository;

import at.htl.model.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {

    public void update(Customer customer) {
        getEntityManager().merge(customer);
    }

}

package at.htl.resource;

import at.htl.model.Customer;
import at.htl.repository.CustomerRepository;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Objects;

@Path("/api/customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
//@RolesAllowed("customer")
public class CustomerResource {

    @Inject
    CustomerRepository customerRepository;

    @GET
    @Path("/{id}")
    public Response getCustomer(@PathParam("id") long id)  {
        Customer customer = this.customerRepository.findById(id);
        return Response.ok(Objects.requireNonNullElse(customer, "Customer does not exist")).build();
    }

    @POST
    @Transactional
    @Path("/")
    public Response postCustomer(Customer customer, @Context UriInfo uriInfo) {
        customer.id = null;
        this.customerRepository.persist(customer);
        return Response.created(uriInfo.getAbsolutePathBuilder().path(
                Long.toString(customer.id)).build()).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response putCustomer(@PathParam("id") long id, Customer customer) {
        if (this.customerRepository.findById(id) == null) {
            return Response.ok("Customer not found").status(400).build();
        }
        if (customer.id != null) {
            return Response.ok("Customer you want to update cannot have an id").status(400).build();
        }
        customer.id = id;
        this.customerRepository.update(customer);
        return Response.ok(customer).status(200).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") long id) {
        if (this.customerRepository.findById(id) == null) {
            return Response.ok("Customer not found").status(400).build();
        }
        return Response.ok(this.customerRepository.deleteById(id)?
                        "Customer successfully deleted" : "Something went wrong. Try again").build();
    }

}

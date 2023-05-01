package at.htl.boundary;

import at.htl.model.Person;
import at.htl.model.RegisterDTO;
import at.htl.repository.CoursePlanRepository;
import at.htl.repository.CourseRepository;
import at.htl.repository.PersonRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/course")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CourseResource {

    // TODO REST-Schnittstelle definieren
    // TODO Datenbankzugriffe über Repository-Klasse durchführen

    @Inject
    CourseRepository courseRepository;
    @Inject
    CoursePlanRepository coursePlanRepository;
    @Inject
    PersonRepository personRepository;

    @GET
    @Path("/search/{text}")
    public Response search(@PathParam("text") String text) {
        return Response.ok(courseRepository.search(text)).build();
    }

    @GET
    @Path("/{id}")
    public Response searchById(@PathParam("id") String id) {
        return Response.ok(courseRepository.find("id", id).firstResult()).build();
    }

    @GET
    @Path("/plan/{id}")
    public Response searchCoursePlaneById(@PathParam("id") Long id) {
        return Response.ok(coursePlanRepository.findById(id)).build();
    }

    @POST
    @Path("/plan")
    @Transactional
    public Response register(RegisterDTO registration, @Context UriInfo context) {
        Person person = personRepository
                .find("firstname like ?1 and lastname like ?2",
                        registration.firstname, registration.lastname)
                .firstResult();
        if(person == null) person = personRepository.add(registration.firstname, registration.lastname);
        coursePlanRepository.addAttendant(registration.planId, person);
        URI uri = context.getAbsolutePathBuilder().path(Long.toString(registration.planId)).build();
        return Response.created(uri).build();
    }

}

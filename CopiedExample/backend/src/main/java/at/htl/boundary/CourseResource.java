package at.htl.boundary;

import at.htl.model.Course;
import at.htl.model.CourseDTO;
import at.htl.model.CoursePlan;
import at.htl.model.Person;
import at.htl.repository.CoursePlanRepository;
import at.htl.repository.CourseRepository;
import at.htl.repository.PersonRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/course/")
@Consumes("application/json")
@Produces("application/json")
public class CourseResource {


    @Inject
    PersonRepository personRepository;
    @Inject
    CoursePlanRepository coursePlanRepository;
    @Inject
    CourseRepository courseRepository;


    @GET
    @Path("/search/{text}")
    public List<Course> searchCourse(@PathParam("text") String text) {
        return this.courseRepository.search(text);
    }

    @GET
    @Path("/{id}")
    public Course getCourseById(@PathParam("id") String id) {
        return this.courseRepository.findById(id);
    }

    @GET
    @Path("plan/{id}")
    public List<CoursePlan> getCourseplanById(@PathParam("id") String id) {

        return this.coursePlanRepository.findByCourseId(id);
    }

    @POST
    @Path("/plan")
    @Transactional

    public Response addCourse(CourseDTO data) {
        Person p = personRepository.findByName(data.firstname, data.lastname);
        if (p == null) {
            p = new Person(data.firstname, data.lastname);
            personRepository.persist(p);
        }
        CoursePlan c = coursePlanRepository.findById(data.planId);
        c.personList.add(p);
        return Response.accepted().build();
    }

    @GET
    @Path("/person/all")
    public List<Person> getAllPersons() {
        return this.personRepository.findAll().stream().toList();
    }

    @GET
    @Path("/person/{id}")
    public Person getPersonById(@PathParam("id") long id) {
        return this.personRepository.findById(id);
    }

}



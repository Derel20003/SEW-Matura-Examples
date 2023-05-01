package at.htl.boundary;

import at.htl.DTO.RegisterDTO;
import at.htl.DTO.SchoolClassDTO;
import at.htl.DTO.StudentDTO;
import at.htl.DTO.StudentToClassDTO;
import at.htl.model.*;
import at.htl.repository.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/")
public class CourseResource {

    @Inject
    CoursePlanRepository coursePlanRepository;
    @Inject
    CourseRepository courseRepository;
    @Inject
    EnrollmentRepo enrollmentRepo;
    @Inject
    PersonRepository personRepository;
    @Inject
    SchooclassRepo schooclassRepo;
    @Inject
    StudentRepo studentRepo;
    @Inject
    EmasWebsockets sockets;

    @GET
    @Path("enrollment/all")
    public List<Enrollment> getAllEnrollments() {
        return this.enrollmentRepo.findAll().stream().toList();
    }

    @GET
    @Path("class/all")
    public List<SchoolClass> getAllClasses() {
        return this.schooclassRepo.findAll().stream().toList();
    }

    @GET
    @Path("student/all")
    public List<Student> getAllStudent() {
        return this.studentRepo.findAll().stream().toList();
    }

    @POST
    @Path("class/new")
    @Transactional
    public Response newClass(SchoolClassDTO data) {

        SchoolClass sc = new SchoolClass(data.title, data.description);
        this.schooclassRepo.persist(sc);
        return Response.accepted().build();
    }

    @POST
    @Path("student/new")
    @Transactional
    public Response newStudent(StudentDTO data) {
        Student st = new Student(data.firstname, data.lastname);
        this.studentRepo.persist(st);
        return Response.accepted().build();
    }

    @PUT
    @Path("class/addStudent/{zweig}")
    @Transactional
    public Response addStudentToclass(@PathParam("zweig") String zweig, StudentToClassDTO data) {

        Student student = this.studentRepo.findbyName(data.firstname, data.lastname);
        if (student == null) {
            student = new Student(data.firstname, data.lastname);
            this.studentRepo.persist(student);
        }
        SchoolClass schoolClass = this.schooclassRepo.findById(data.classId);
        if (schoolClass == null) {
            return Response.serverError().build();
        }

        Enrollment enrollment = new Enrollment();

        enrollment.student = student;
        enrollment.scClass = schoolClass;
        enrollment.zweig = zweig;

        student.enrollments.add(enrollment);



        this.enrollmentRepo.persist(enrollment);

        this.sockets.broadcastOBJ(this.studentRepo.findbyName(data.firstname, data.lastname));
        return Response.ok().build();

    }

    @GET
    @Path("student/{classname}")
    public List<StudentDTO> getStudentsPerClass(@PathParam("classname") String name) {
        List<StudentDTO> students = this.enrollmentRepo.findByClass(name);
        return students;
    }

    @GET
    @Path("/course/search/{text}")
    public List<Course> searchCourses(@PathParam("text") String text) {
        return this.courseRepository.search(text);
    }

    @GET
    @Path("/course/{id}")
    public Course searchCourseById(@PathParam("id") String id) {
        return this.courseRepository.findById(id);
    }

    @GET
    @Path("/course/plan/{id}")
    public List<CoursePlan> getCourseplanById(@PathParam("id") String id) {
        return this.coursePlanRepository.findByCourseId(id);
    }

    @POST
    @Transactional
    @Path("/course/plan")
    public Response addCourseplan(RegisterDTO data) {
        Person p = this.personRepository.findByName(data.firstname, data.lastname);
        if (p == null) {
            p = new Person(data.firstname, data.lastname);
            this.personRepository.persist(p);
        }

        CoursePlan c = this.coursePlanRepository.findById(data.planId);

        c.personList.add(p);
        return Response.ok().build();
    }

    @GET
    @Path("course/getall")
    public List<Person> getAllPerson(){
        return this.personRepository.findAll().stream().toList();
    }


    @DELETE
    @Transactional
    @Path("/student/delete")
    public Response deleteStudent(StudentDTO data){
        Student student = this.studentRepo.findbyName(data.firstname, data.lastname);

        if(student ==null){
                return Response.serverError().build();
        }
        this.studentRepo.delete(student);
        return Response.ok().build();

    }

}

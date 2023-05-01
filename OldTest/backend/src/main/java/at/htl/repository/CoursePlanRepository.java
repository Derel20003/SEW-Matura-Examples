package at.htl.repository;

import at.htl.model.CoursePlan;
import at.htl.model.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CoursePlanRepository implements PanacheRepository<CoursePlan> {

    public List<CoursePlan> findByCourseId(String id) {
        // TODO soll alle geplanten Kurse mit der übergebenen Kurs-ID zurückliefern
        throw new RuntimeException("Not implemented yet");
    }

    public void addAttendant(Long planId, Person person) {
        CoursePlan cp = findById(planId);
        if (cp != null) {
            if (!cp.attendants.contains(person)) cp.attendants.add(person);
        }
    }
}

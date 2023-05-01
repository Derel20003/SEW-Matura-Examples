package at.htl.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EnrollmentId implements Serializable {

    public long student_id;
    public long class_ID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentId that = (EnrollmentId) o;
        return student_id == that.student_id && class_ID == that.class_ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(student_id, class_ID);
    }
}

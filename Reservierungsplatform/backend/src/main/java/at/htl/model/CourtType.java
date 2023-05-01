package at.htl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class CourtType extends PanacheEntity {

    public String description;

    @OneToMany(mappedBy = "courtType", cascade = CascadeType.ALL)
    @JsonIgnore
    public List<Court> courtList;

    public CourtType() {
    }

    public CourtType(String description, List<Court> courtList) {
        this.description = description;
        this.courtList = courtList;
    }
}

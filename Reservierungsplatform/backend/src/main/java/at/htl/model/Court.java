package at.htl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Court extends PanacheEntity {

    public String location;

    @ManyToOne
    @JoinColumn(name = "courttype_id")
    public CourtType courtType;

    @OneToMany(mappedBy = "court", cascade = CascadeType.ALL)
    @JsonIgnore
    public List<Reservation> reservationList;

    public Court() {
    }

    public Court(String location, CourtType courtType, List<Reservation> reservationList) {
        this.location = location;
        this.courtType = courtType;
        this.reservationList = reservationList;
    }
}

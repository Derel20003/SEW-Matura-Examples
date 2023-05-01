package at.htl.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Reservation extends PanacheEntity {

    public LocalDateTime start_time;
    public LocalDateTime end_time;
    public LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "court_id")
    public Court court;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    public Customer customer;

    public Reservation() {
    }

    public Reservation(LocalDateTime start_time, LocalDateTime end_time,
                       LocalDateTime timestamp, Court court,
                       Customer customer) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.timestamp = timestamp;
        this.court = court;
        this.customer = customer;
    }
}

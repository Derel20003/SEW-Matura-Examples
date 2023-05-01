package at.htl.model;

import java.time.LocalDateTime;

public class ReservationDTO {

    public LocalDateTime start_time;
    public LocalDateTime end_time;
    public LocalDateTime timestamp;
    public long court_id;
    public long customer_id;

    public ReservationDTO(LocalDateTime start_time,
                          LocalDateTime end_time,
                          LocalDateTime timestamp,
                          long court_id,
                          long customer_id) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.timestamp = timestamp;
        this.court_id = court_id;
        this.customer_id = customer_id;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public long getCourt_id() {
        return court_id;
    }

    public void setCourt_id(long court_id) {
        this.court_id = court_id;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }
}

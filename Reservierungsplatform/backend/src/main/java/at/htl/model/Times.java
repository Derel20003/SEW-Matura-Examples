package at.htl.model;

import java.time.LocalDateTime;

public class Times {

    private LocalDateTime start_time;
    private LocalDateTime end_time;

    public Times(LocalDateTime start_time, LocalDateTime end_time) {
        this.start_time = start_time;
        this.end_time = end_time;
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
}

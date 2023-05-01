package at.htl.repository;

import at.htl.model.Court;
import at.htl.model.Reservation;
import at.htl.model.Times;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ReservationRepository implements PanacheRepository<Reservation> {

    @Inject
    CourtRepository courtRepository;

    public List<Reservation> getAllForUser(long userId) {
        return list("customer.id", userId);
    }

    public void update(Reservation reservation) {
        getEntityManager().merge(reservation);
    }

    public List<Reservation> getReservations(long duration, String date, String sport) {
        LocalDate formattedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("d.MM.yyyy"));
        List<Times> times = getAvailableTimes(duration, formattedDate);



        return null;
    }

    public List<Times> getAvailableTimes(long durationLong, LocalDate date) {
        int hours = 6;
        int startHour = 14;

        System.out.println(this.courtRepository.findById(2L).location);
        System.out.println(this.courtRepository.findCourtsByTypeByDescription("Tennis"));

        int duration = (int) durationLong;
        List<Times> times = new ArrayList<>();
        LocalDateTime time = LocalDateTime.of(date, LocalTime.of(startHour, 0, 0));
        for(int i = 30; i <= 2 * hours * 30; i += 30) {
            Times newTime = new Times(time, time = time.plusMinutes(30));
            times.add(newTime);
        }
        return times;
    }
}

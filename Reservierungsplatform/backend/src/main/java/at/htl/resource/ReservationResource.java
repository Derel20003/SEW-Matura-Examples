package at.htl.resource;

import at.htl.model.*;
import at.htl.repository.CourtRepository;
import at.htl.repository.CustomerRepository;
import at.htl.repository.ReservationRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/api/reservation")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservationResource {

    @Inject
    ReservationRepository reservationRepository;

    @Inject
    CourtRepository courtRepository;

    @Inject
    CustomerRepository customerRepository;

    @POST
    @Transactional
    @Path("/")
    public Response postReservation(ReservationDTO reservation, @Context UriInfo uriInfo) {
        Reservation newReservation = new Reservation();
        newReservation.start_time = reservation.start_time;
        newReservation.end_time = reservation.end_time;
        newReservation.timestamp = reservation.timestamp;

        Court court = this.courtRepository.findById(reservation.court_id);
        Customer customer = this.customerRepository.findById(reservation.customer_id);

        newReservation.court = court;
        newReservation.customer = customer;

        this.reservationRepository.persist(newReservation);
        return Response.created(uriInfo.getAbsolutePathBuilder().path(
                Long.toString(newReservation.id)).build()).build();
    }

    @GET
    @Path("/all-for-user/{id}")
    public List<Reservation> getAllForUser(@PathParam("id") long userId) {
        return reservationRepository.getAllForUser(userId);
    }

    @GET
    @Path("/available-times/")
    public List<Times> getReservations(
            @QueryParam("duration") long duration, @QueryParam("date") String date, @QueryParam("sport") String sport) {
        LocalDate formattedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("d.M.yyyy"));
        return reservationRepository.getAvailableTimes(duration, formattedDate);
    }

    @DELETE
    @Transactional
    @Path("{resid}")
    public Response deleteReservation(@PathParam("resid") long resid) {
        reservationRepository.deleteById(resid);
        return Response.noContent().build();
    }

}

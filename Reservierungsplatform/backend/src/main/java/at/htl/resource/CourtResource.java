package at.htl.resource;

import at.htl.model.Court;
import at.htl.repository.CourtRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

@Path("/api/court")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CourtResource {

   @Inject
    CourtRepository courtRepository;

   @GET
   @Path("/{id}")
   public Response getCourt(@PathParam("id") long id) {
       Court court = this.courtRepository.findById(id);
       return Response.ok(Objects.requireNonNullElse(court, "Court does not exist")).build();
   }

    @GET
    @Path("/courts-by-type/")
    public Response getCourtsByType(@QueryParam("description") String description, @QueryParam("id") Long courttype_id) {
        if (description != null && courttype_id != null) {
            return Response.ok("Cannot search for both description and id").status(400).build();
        }
        if (description == null && courttype_id == null) {
            return Response.ok("Either description or id have to be specified").status(400).build();
        }
        List<Court> courtList = null;
        String typeString = "parameter";
        if (description != null) {
            courtList = this.courtRepository.findCourtsByTypeByDescription(description);
            typeString = "sport";
        }
        if (courttype_id != null) {
            courtList = this.courtRepository.findCourtsByTypeById(courttype_id);
            typeString = "id";
        }
        if (courtList == null || courtList.isEmpty()) {
            return Response.ok(String.format("No courts with this %s exist", typeString)).status(400).build();
        }
        return Response.ok(courtList).build();
    }

}

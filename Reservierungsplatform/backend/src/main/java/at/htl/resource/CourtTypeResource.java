package at.htl.resource;

import at.htl.model.CourtType;
import at.htl.repository.CourtTypeRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("api/courttype")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CourtTypeResource {

    @Inject
    CourtTypeRepository courtTypeRepository;

    @GET
    @Path("/{id}")
    public Response getCourtType(@PathParam("id") long id) {
        CourtType courttype = this.courtTypeRepository.findById(id);
        return Response.ok(Objects.requireNonNullElse(courttype, "Court type does not exist")).build();
    }

    @GET
    @Path("/all")
    public Response getCourtTypes() {
        return Response.ok(this.courtTypeRepository.findAllTypes()).build();
    }

}

package at.htl.resource;

import at.htl.model.CredentialsDTO;
import at.htl.repository.CustomerRepository;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;

import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.Set;

@Path("/api/auth")
public class AuthResource {

    @Inject
    @ConfigProperty(name = "smallrye.jwt.new-token.lifespan")
    long lifespan;

    @Inject
    CustomerRepository customerRepository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response login(CredentialsDTO credentials) {
        long anz = customerRepository.find("id=?1 and password=?2",
                Long.parseLong(credentials.getUserid()),
                credentials.getPassword())
                .count();
        if (anz != 1) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        long exp = Instant.now().getEpochSecond() + lifespan;
        String token = Jwt
                .claim(Claims.upn.name(), credentials.getUserid())
                .groups(Set.of("customer", "admin"))
                .sign();
        String entity = Json.createObjectBuilder()
                .add("token", token)
                .add("expires_at", exp)
                .build().toString();
        return Response.ok().entity(entity).build();
    }

}

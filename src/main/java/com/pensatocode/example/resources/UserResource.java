package com.pensatocode.example.resources;

import com.pensatocode.example.api.Country;
import com.pensatocode.example.filters.CountryAllowed;
import com.pensatocode.example.filters.CountryRequired;
import com.pensatocode.example.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@CountryRequired
public class UserResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

    private final int defaultSize;

    public UserResource(int defaultSize) {
        this.defaultSize = defaultSize;
        LOGGER.info("############## UserResource Constructor ##############");
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_JSON)
    @CountryAllowed(values = {Country.USA})
    public Response combineUserData(User user) {
        LOGGER.info("############## User: " + user.toString());
        // Combine user object data
        String result = String.format("User name is %s and address is %s.",
                user.getPersonalData().getName(),
                user.getAddress().getStreet());
        return Response.status(200).entity(result).build();
    }
}

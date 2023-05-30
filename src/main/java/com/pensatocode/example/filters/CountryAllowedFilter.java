package com.pensatocode.example.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CountryAllowedFilter implements ContainerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryAllowedFilter.class);

    /**
     * This filter will be executed only if the method is annotated with @CountryAllowed.
     */
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        LOGGER.info("############## Path: " + requestContext.getUriInfo().getPath());
    }
}

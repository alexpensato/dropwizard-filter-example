package com.pensatocode.example.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
@Provider
public class DateNotSpecifiedFilter implements ContainerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateNotSpecifiedFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        final String dateHeader = requestContext.getHeaderString(HttpHeaders.DATE);
        if (dateHeader == null) {
//            throw new WebApplicationException(
//            new IllegalArgumentException("Date Header was not specified"), Response.Status.BAD_REQUEST);
            LOGGER.warn("############## Date Header was not specified!");
        } else {
            LOGGER.info("############## Date Header: " + dateHeader);
        }
    }
}

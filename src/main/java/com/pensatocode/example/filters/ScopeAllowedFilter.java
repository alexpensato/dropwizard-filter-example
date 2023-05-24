package com.pensatocode.example.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class ScopeAllowedFilter implements ContainerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScopeAllowedFilter.class);

    /**
     * This filter will be executed only if the method is annotated with @ScopeAllowed.
     */
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String id = null;
        MultivaluedMap<String, String> queryParams = requestContext.getUriInfo().getQueryParameters();
        for (String key : queryParams.keySet()) {
            if (key.equals("id")) {
                id = queryParams.getFirst(key);
            }
            String value = queryParams.getFirst(key);
            LOGGER.info("############## QueryParam: " + key + " = " + value);
        }
        MultivaluedMap<String, String> pathParams = requestContext.getUriInfo().getPathParameters();
        for (String key : pathParams.keySet()) {
            if (key.equals("id")) {
                id = pathParams.getFirst(key);
            }
            String value = pathParams.getFirst(key);
            LOGGER.info("############## PathParam: " + key + " = " + value);
        }
        if (id == null) {
            LOGGER.info("############## Method does not contain parameter called id.");
            return;
        }
        long lId = 0L;
        try {
            lId = Long.parseLong(id);
        } catch (NumberFormatException e1) {
            LOGGER.warn("############## Param id is not of type Long. It is: " + id.getClass().getName());
            return;
        }
        if (lId < 1 || lId > 5) {
            LOGGER.warn("############## Param id is out of scope: " + id);
            return;
        }
        LOGGER.info("############## All good with parameter id.");
    }
}

package com.pensatocode.example.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Modifier;
import java.util.Arrays;

@Provider
public class CountryRequiredFeature implements DynamicFeature {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryRequiredFeature.class);

    /**
     * Whenever a resource class is annotated with @CountryRequired,
     * the non-void public methods must also be annotated with @CountryAllowed.
     * And methods annotated with @CountryAllowed will have the CountryAllowedFilter registered.
     */
    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        if (resourceInfo.getResourceClass().getAnnotation(CountryRequired.class) == null) {
            return;
        }
        if (!Modifier.isPublic(resourceInfo.getResourceMethod().getModifiers())) {
            return;
        }
        if (resourceInfo.getResourceMethod().getReturnType().equals(Void.TYPE)) {
            return;
        }
        CountryAllowed annotation = resourceInfo.getResourceMethod().getAnnotation(CountryAllowed.class);
        if (annotation != null) {
            LOGGER.info(String.format("=====> Class: %s, Method: %s, CountryAllowed Annotation Values: %s",
                            resourceInfo.getResourceClass().getName(),
                            resourceInfo.getResourceMethod().getName(),
                            Arrays.toString(annotation.values())));
            context.register(CountryAllowedFilter.class);
        } else {
            LOGGER.warn("############## CountryAllowed annotation was not specified!");
            throw new WebApplicationException(
                    new IllegalArgumentException("CountryAllowed annotation was not specified"),
                    Response.Status.BAD_REQUEST
            );
        }
    }
}

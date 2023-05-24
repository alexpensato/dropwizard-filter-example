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
public class ScopeRequiredFeature implements DynamicFeature {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScopeRequiredFeature.class);

    /**
     * Whenever a resource class is annotated with @ScopeRequired,
     * the non-void public methods must also be annotated with @ScopeAllowed.
     * And methods annotated with @ScopeAllowed will have the ScopeAllowedFiltered registered.
     */
    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        if (resourceInfo.getResourceClass().getAnnotation(ScopeRequired.class) == null) {
            return;
        }
        if (!Modifier.isPublic(resourceInfo.getResourceMethod().getModifiers())) {
            return;
        }
        if (resourceInfo.getResourceMethod().getReturnType().equals(Void.TYPE)) {
            return;
        }
        ScopeAllowed annotation = resourceInfo.getResourceMethod().getAnnotation(ScopeAllowed.class);
        if (annotation != null) {
            LOGGER.info(String.format("=====> Class: %s, Method: %s, ScopeAllowed Annotation Values: %s",
                            resourceInfo.getResourceClass().getName(),
                            resourceInfo.getResourceMethod().getName(),
                            Arrays.toString(annotation.values())));
            context.register(ScopeAllowedFilter.class);
        } else {
            LOGGER.warn("############## ScopeAllowed annotation was not specified!");
            throw new WebApplicationException(
                    new IllegalArgumentException("ScopeAllowed annotation was not specified"),
                    Response.Status.BAD_REQUEST
            );
        }
    }
}

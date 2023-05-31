package com.pensatocode.example.filters;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pensatocode.example.api.Country;
import com.pensatocode.example.model.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.io.*;
import java.util.Arrays;
import java.util.List;

@Provider
public class CountryAllowedFilter implements ContainerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryAllowedFilter.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final List<Country> allowedCountries;

    public CountryAllowedFilter(Country[] allowedCountries) {
        this.allowedCountries = Arrays.asList(allowedCountries);
    }

    /**
     * This filter will be executed only if the method is annotated with @CountryAllowed.
     */
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        LOGGER.info("############## Path: " + requestContext.getUriInfo().getPath());

        if (!requestContext.getMediaType().equals(MediaType.APPLICATION_JSON_TYPE)) {
            LOGGER.warn("############## Media type is not JSON.");
            return;
        }

        // Get the input stream of the request entity
        InputStream inputStream = requestContext.getEntityStream();
        if (inputStream == null) {
            LOGGER.warn("############## Request body is empty.");
            return;
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }
        byte[] requestBody = outputStream.toByteArray();
        // This could be simplified with Apache Commons IOUtils:
        // byte[] requestBody = IOUtils.toByteArray(inputStream);

        // Set the new input stream as the request entity stream
        requestContext.setEntityStream(new ByteArrayInputStream(requestBody));

        // Read the JSON payload into an Address object and set it as a property of the request context
        Address address;
        try {
            Object payload = objectMapper.readValue(requestBody, Object.class);
            JsonNode rootNode = objectMapper.valueToTree(payload);
            JsonNode addressNode = rootNode.get("address");
            if (addressNode != null) {
                address = objectMapper.treeToValue(addressNode, Address.class);
//                requestContext.setProperty("address", address);
            } else {
                LOGGER.warn("############## Address is null.");
                return;
            }
        } catch (Exception e) {
            // handle exception
            LOGGER.warn("############## Failed to get Address from JSON: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        Country requestCountry;
        try {
            requestCountry = Country.valueOf(address.getCountry());
        } catch (IllegalArgumentException e) {
            requestCountry = Country.UNKNOWN;
        }
        if (allowedCountries.contains(requestCountry)) {
            LOGGER.info("############## Country is allowed: " + address.getCountry());
        } else {
            LOGGER.warn("############## Country is not allowed: " + address.getCountry());
        }
    }
}

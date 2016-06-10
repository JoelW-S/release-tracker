package com.github.joelws.release.tracker.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.joelws.release.tracker.exception.ReleaseTrackerException;
import com.github.joelws.release.tracker.handler.JsonResponse;
import com.github.joelws.release.tracker.interfaces.JsonAdapter;

import org.apache.log4j.Logger;

import java.io.IOException;

public class JsonAdapterImpl implements JsonAdapter {
    private static final Logger LOGGER = Logger.getLogger(JsonAdapterImpl.class);

    @Override
    public String getJsonFromObject(Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            LOGGER.info("Unable to map object to JSON");
            throw new ReleaseTrackerException(new JsonResponse(400, "Invalid format"));
        }
    }

    @Override
    public <T> T getObjectFromJson(String jsonString, Class<T> klazz) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final T obj = mapper.readValue(jsonString, klazz);
            return obj;

        } catch (IOException e) {
            LOGGER.info("Unable to map JSON to object");
            throw new ReleaseTrackerException(new JsonResponse(400, "Invalid format"));
        }
    }
}

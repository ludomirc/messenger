package org.qbit.messanger.userservice.fixture;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;

public final class JsonHelper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonHelper() {
    }

    public static <T>  T getObject(ResultActions result, Class<T> tClass) throws UnsupportedEncodingException, JsonProcessingException {

        return objectMapper.readValue(result.andReturn().getResponse().getContentAsString(),tClass);
    }

    public static String asJsonString(final Object obj) {
        try {
            final String jsonContent = objectMapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
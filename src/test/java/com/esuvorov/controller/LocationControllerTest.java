package com.esuvorov.controller;

import com.esuvorov.model.Location;
import org.junit.Test;
import org.springframework.mock.http.MockHttpOutputMessage;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LocationControllerTest extends ControllerTest {
//
//    @Test
//    public void getLocations() throws Exception {
//
//    }
//
//    @Test
//    public void getLocation() throws Exception {
//    }
//
//    @Test
//    public void avg() throws Exception {
//    }

    @Test
    public void locationNotFound() throws Exception {
        mockMvc.perform(post("/locations/")
                .content(json(new Location()))
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        mappingJackson2HttpMessageConverter.write(
                o, APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
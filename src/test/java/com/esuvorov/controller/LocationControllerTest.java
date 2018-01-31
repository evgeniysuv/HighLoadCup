package com.esuvorov.controller;

import com.esuvorov.model.Location;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LocationControllerTest extends ControllerTest {

    @Test
    public void getLocations() throws Exception {
        // TODO: 1/20/18  Need to restrict result
    }

    @Test
    public void getLocation() throws Exception {
        String json = getResultJson("/locations/1");
        ObjectMapper mapper = new ObjectMapper();
        Location location = mapper.readValue(json, Location.class);
        Assert.assertNotNull(location);
    }

    @Test
    public void getAverageMark() throws Exception {
        String result = getResultJson("/locations/4/avg?visitedAt=1152373529");
        Map<String, Double> avgMark = new ObjectMapper().readValue(result, new TypeReference<Map<String, Double>>() {
        });
        Assert.assertTrue(!avgMark.isEmpty());
        Double value = avgMark.get("avg");
        Assert.assertEquals((Double) 2.19, value);
    }

    @Test
    public void locationNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/locations/9999999")
                .content(json(new Location()))
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @SuppressWarnings("unchecked")
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        mappingJackson2HttpMessageConverter.write(
                o, APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }


}
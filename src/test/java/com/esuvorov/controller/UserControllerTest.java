package com.esuvorov.controller;

import com.esuvorov.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends ControllerTest {

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        mappingJackson2HttpMessageConverter = Arrays.stream(converters)
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null", mappingJackson2HttpMessageConverter);
    }
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
    public void userNotFound() throws Exception {
        String notExistingUserId = "99999999";
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + notExistingUserId)
                .content(json(new User()))
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

}
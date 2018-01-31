package com.esuvorov.controller;

import com.esuvorov.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends ControllerTest {

    @Test
    public void getUser() throws Exception {
        String userJson = getResultJson("users/1");
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(userJson, User.class);
        Assert.assertNotNull(user);
        Assert.assertEquals("Светлана", user.getFirstName());
    }

    @Test
    public void getUserByFilter() {

    }

    @Test
    public void updateUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/5")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content("  {\n" +
                        "        \"email\": jessiepinkman@gmail.com,\n" +
                        "        \"first_name\": \"Jessie\",\n" +
                        "        \"birth_date\": 616550400\n" +
                        "    } "))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUserWithEmptyEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/5")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content("  {\n" +
                        "        \"email\": null,\n" +
                        "        \"first_name\": \"Jessie\",\n" +
                        "        \"last_name\": \"Pinkman\",\n" +
                        "        \"birth_date\": 616550400\n" +
                        "    } "))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void userNotFound() throws Exception {
        String notExistingUserId = "99999999";
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + notExistingUserId)
                .content(json(new User()))
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

}
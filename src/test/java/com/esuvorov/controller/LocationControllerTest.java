package com.esuvorov.controller;

import com.esuvorov.HighLoadCupApplication;
import com.esuvorov.model.Location;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HighLoadCupApplication.class)
@WebAppConfiguration
public class LocationControllerTest {

    private MediaType contentType = new MediaType(APPLICATION_JSON.getType(),
            APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private String userName = "bdussault";

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

//    private Account account;
//
//    private List<Bookmark> bookmarkList = new ArrayList<>();
//
//    @Autowired
//    private LocationRepository locationRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;
//
//    @Autowired
//    private AccountRepository accountRepository;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
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

    @Before
    public void setup() throws Exception {
        mockMvc = webAppContextSetup(webApplicationContext).build();

//        this.locationRepository.deleteAllInBatch();
//        this.accountRepository.deleteAllInBatch();
//
//        this.account = accountRepository.save(new Account(userName, "password"));
//        this.bookmarkList.add(locationRepository.save(new Bookmark(account, "http://bookmark.com/1/" + userName, "A description")));
//        this.bookmarkList.add(locationRepository.save(new Bookmark(account, "http://bookmark.com/2/" + userName, "A description")));
    }

}
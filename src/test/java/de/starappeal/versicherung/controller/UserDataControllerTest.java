package de.starappeal.versicherung.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.starappeal.versicherung.entities.UserData;
import de.starappeal.versicherung.services.UserDataService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
class UserDataControllerTest {

  @Autowired private MockMvc mvc;

  @Autowired private UserDataService userDataService;

  @BeforeEach
  void setUp() {
    // stupid hack sadly :(
    if (userDataService.findById(1L) == null) {
      userDataService.save(new UserData(1L, 1000, "LKW", "53757", 0.5d));
    }
    if (userDataService.findById(2L) == null) {
      userDataService.save(new UserData(2L, 2000, "PKW", "53721", 2.5d));
    }
  }

  @Test
  void getAll() throws Exception {
    mvc.perform(get("/api/userdata/").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[0].kilometer", is(1000)))
        .andExpect(jsonPath("$[0].vehicleType", is("LKW")))
        .andExpect(jsonPath("$[0].zipCode", is("53757")))
        .andExpect(jsonPath("$[0].calculatedFactor", is(0.5d)))
        .andExpect(jsonPath("$[1].id", is(2)))
        .andExpect(jsonPath("$[1].kilometer", is(2000)))
        .andExpect(jsonPath("$[1].vehicleType", is("PKW")))
        .andExpect(jsonPath("$[1].zipCode", is("53721")))
        .andExpect(jsonPath("$[1].calculatedFactor", is(2.5d)));
  }

  @Test
  void findById() throws Exception {
    mvc.perform(get("/api/userdata/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("id", is(1)))
        .andExpect(jsonPath("kilometer", is(1000)))
        .andExpect(jsonPath("vehicleType", is("LKW")))
        .andExpect(jsonPath("zipCode", is("53757")))
        .andExpect(jsonPath("calculatedFactor", is(0.5d)));
  }

  @Test
  void findById_NotFound() throws Exception {
    mvc.perform(get("/api/userdata/5")).andExpect(status().isNotFound());
  }
}

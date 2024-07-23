package de.starappeal.versicherung.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.starappeal.versicherung.controller.request.CalculateInsuranceRequest;
import de.starappeal.versicherung.entities.Region;
import de.starappeal.versicherung.entities.VehicleType;
import de.starappeal.versicherung.services.InsuranceCalculationService;
import de.starappeal.versicherung.services.RegionService;
import de.starappeal.versicherung.services.UserDataService;
import de.starappeal.versicherung.services.VehicleTypeService;
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
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class InsuranceControllerTest {

  @Autowired private MockMvc mvc;
  @Autowired private RegionService regionService;
  @Autowired private VehicleTypeService vehicleTypeService;
  @Autowired private InsuranceCalculationService insuranceCalculationService;

  @Autowired private UserDataService userDataService;

  @Test
  void calculateInsurance() throws Exception {
    Region region =
        regionService.save(new Region(1L, "State", "country", "city", "53757", "location", 1.0d));
    VehicleType vehicleType = vehicleTypeService.save(new VehicleType(1L, "LKW", 1.5d));

    CalculateInsuranceRequest request =
        new CalculateInsuranceRequest(2000, region.getZipCode(), vehicleType.getName());

    mvc.perform(
            post("/api/insurance/calculate")
                .content(new ObjectMapper().writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("response.kilometer", is(request.kilometer())))
        .andExpect(jsonPath("response.vehicleType", is(request.vehicleType())))
        .andExpect(jsonPath("response.zipCode", is(request.zipcode())))
        .andExpect(
            jsonPath(
                "response.calculatedInsurance",
                is(
                    insuranceCalculationService.calculateInsurance(
                        request.kilometer(), request.zipcode(), request.vehicleType()))));
  }
}

package de.starappeal.versicherung.controller;

import de.starappeal.versicherung.controller.request.CalculateInsuranceRequest;
import de.starappeal.versicherung.controller.response.CalculateInsuranceResponse;
import de.starappeal.versicherung.entities.UserData;
import de.starappeal.versicherung.services.InsuranceCalculationService;
import de.starappeal.versicherung.services.UserDataService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/insurance")
public class InsuranceController {
  private static final Logger logger = LoggerFactory.getLogger(InsuranceController.class);

  private final InsuranceCalculationService insuranceCalculationService;
  private final UserDataService userDataService;

  @Autowired
  public InsuranceController(
      InsuranceCalculationService insuranceCalculationService, UserDataService userDataService) {
    this.insuranceCalculationService = insuranceCalculationService;
    this.userDataService = userDataService;
  }

  @ResponseBody
  @PostMapping("/calculate")
  public ResponseEntity<CalculateInsuranceResponse> calculateInsurance(
      @RequestBody CalculateInsuranceRequest request) {
    logger.info("Insurance calculation request: {}", request);
    UserData userdata;
    try {
      double factor =
          insuranceCalculationService.calculateInsurance(
              request.kilometer(), request.zipcode(), request.vehicleType());

      UserData dataToSave =
          new UserData(request.kilometer(), request.vehicleType(), request.zipcode(), factor);

      userdata = userDataService.save(dataToSave);
    } catch (IllegalArgumentException e) {
      logger.error("There was an error: ", e);
      return ResponseEntity.badRequest().body(new CalculateInsuranceResponse(e));
    } catch (EntityNotFoundException e){
      logger.info(e.getMessage());
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(new CalculateInsuranceResponse(userdata));
  }
}

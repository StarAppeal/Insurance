package de.starappeal.versicherung.controller;

import de.starappeal.versicherung.controller.request.CalculateInsuranceRequest;
import de.starappeal.versicherung.controller.response.CalculateInsuranceResponse;
import de.starappeal.versicherung.services.InsuranceCalculationService;
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

  private final InsuranceCalculationService service;

  @Autowired
  public InsuranceController(InsuranceCalculationService service) {
    this.service = service;
  }

  @ResponseBody
  @PostMapping("/calculate")
  public ResponseEntity<CalculateInsuranceResponse> calculateInsurance(
      @RequestBody CalculateInsuranceRequest request) {
    logger.info("Insurance calculation request: {}", request);
    double factor;
    try {
      factor =
          service.calculateInsurance(request.kilometer(), request.zipcode(), request.vehicleType());

      // persist the request and the factor in the db
    } catch (IllegalArgumentException | EntityNotFoundException e) {
      logger.error("There was an error: ", e);
      return ResponseEntity.badRequest().body(new CalculateInsuranceResponse(e));
    }

    return ResponseEntity.ok(new CalculateInsuranceResponse(factor));
  }
}

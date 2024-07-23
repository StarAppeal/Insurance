package de.starappeal.versicherung.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public record CalculateInsuranceResponse(double factor, String error) {

  public CalculateInsuranceResponse(double factor) {
    this(factor, null);
  }

  public CalculateInsuranceResponse(Exception e) {
    this(0d, e.getMessage());
  }
}

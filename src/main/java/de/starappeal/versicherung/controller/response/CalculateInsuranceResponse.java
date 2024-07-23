package de.starappeal.versicherung.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import de.starappeal.versicherung.entities.UserData;

@JsonInclude(Include.NON_NULL)
public record CalculateInsuranceResponse(UserDataResponse response, String error) {

  public CalculateInsuranceResponse(UserData userData) {
    this(new UserDataResponse(userData), null);
  }

  public CalculateInsuranceResponse(Exception e) {
    this(null, e.getMessage());
  }

  private static class UserDataResponse {
    private final Long id;
    private final int kilometer;
    private final String vehicleType;
    private final String zipCode;
    private final double calculatedInsurance;

    public UserDataResponse(UserData userData) {
      this.id = userData.getId();
      this.kilometer = userData.getKilometer();
      this.vehicleType = userData.getVehicleType();
      this.zipCode = userData.getZipCode();
      this.calculatedInsurance = userData.getCalculatedFactor();
    }

    public Long getId() {
      return id;
    }

    public int getKilometer() {
      return kilometer;
    }

    public String getVehicleType() {
      return vehicleType;
    }

    public String getZipCode() {
      return zipCode;
    }

    public double getCalculatedInsurance() {
      return calculatedInsurance;
    }
  }
}

package de.starappeal.versicherung.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

// Maybe userdata?
@Entity
public class UserData {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private int kilometer;
  private String vehicleType;
  private String zipCode;
  private double calculatedFactor;

  public UserData() {
    // for JPA
  }

  public UserData(int kilometer, String vehicleType, String zipCode, double calculatedFactor) {
    this(null, kilometer, vehicleType, zipCode, calculatedFactor);
  }

  public UserData(
      Long id, int kilometer, String vehicleType, String zipCode, double calculatedFactor) {
    this.id = id;
    this.kilometer = kilometer;
    this.vehicleType = vehicleType;
    this.zipCode = zipCode;
    this.calculatedFactor = calculatedFactor;
  }


  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (UserData) obj;
    return Objects.equals(this.id, that.id)
        && this.kilometer == that.kilometer
        && Objects.equals(this.vehicleType, that.vehicleType)
        && Objects.equals(this.zipCode, that.zipCode)
        && Double.doubleToLongBits(this.calculatedFactor)
            == Double.doubleToLongBits(that.calculatedFactor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, kilometer, vehicleType, zipCode, calculatedFactor);
  }

  @Override
  public String toString() {
    return "UserData["
        + "id="
        + id
        + ", "
        + "kilometer="
        + kilometer
        + ", "
        + "vehicleType="
        + vehicleType
        + ", "
        + "zipCode="
        + zipCode
        + ", "
        + "calculatedFactor="
        + calculatedFactor
        + ']';
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getKilometer() {
    return kilometer;
  }

  public void setKilometer(int kilometer) {
    this.kilometer = kilometer;
  }

  public String getVehicleType() {
    return vehicleType;
  }

  public void setVehicleType(String vehicleType) {
    this.vehicleType = vehicleType;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public double getCalculatedFactor() {
    return calculatedFactor;
  }

  public void setCalculatedFactor(double calculatedFactor) {
    this.calculatedFactor = calculatedFactor;
  }
}

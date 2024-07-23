package de.starappeal.versicherung.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public final class Region {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String state;
  private String country;
  private String city;
  private String zipCode;
  private String location;
  private Double factor;

  public Region(
      Long id,
      String state,
      String country,
      String city,
      String zipCode,
      String location,
      Double factor) {
    this.id = id;
    this.state = state;
    this.country = country;
    this.city = city;
    this.zipCode = zipCode;
    this.location = location;
    this.factor = factor;
  }

  public Region(
      String state, String country, String city, String zipCode, String location, double factor) {
    this(null, state, country, city, zipCode, location, factor);
  }

  public Region() {}

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (Region) obj;
    return Objects.equals(this.id, that.id)
        && Objects.equals(this.state, that.state)
        && Objects.equals(this.country, that.country)
        && Objects.equals(this.city, that.city)
        && Objects.equals(this.zipCode, that.zipCode)
        && Objects.equals(this.location, that.location)
        && Objects.equals(this.factor, that.factor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, state, country, city, zipCode, location, factor);
  }

  @Override
  public String toString() {
    return "Region["
        + "id="
        + id
        + ", "
        + "state="
        + state
        + ", "
        + "country="
        + country
        + ", "
        + "city="
        + city
        + ", "
        + "zipCode="
        + zipCode
        + ", "
        + "location="
        + location
        + ", "
        + "factor="
        + factor
        + ']';
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Double getFactor() {
    return factor;
  }

  public void setFactor(Double factor) {
    this.factor = factor;
  }
}

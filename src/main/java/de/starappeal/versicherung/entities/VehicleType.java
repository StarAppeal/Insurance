package de.starappeal.versicherung.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class VehicleType {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
  private double factor;

  public VehicleType() {
    this(null, 0d);
  }

  public VehicleType(String name, double factor) {
    this(null, name, factor);
  }

  public VehicleType(Long id, String name, double factor) {
    this.id = id;
    this.name = name;
    this.factor = factor;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getFactor() {
    return factor;
  }

  public void setFactor(double factor) {
    this.factor = factor;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (VehicleType) obj;
    return Objects.equals(this.id, that.id)
        && Objects.equals(this.name, that.name)
        && Double.doubleToLongBits(this.factor) == Double.doubleToLongBits(that.factor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, factor);
  }

  @Override
  public String toString() {
    return "VehicleType[" + "id=" + id + ", " + "name=" + name + ", " + "factor=" + factor + ']';
  }
}

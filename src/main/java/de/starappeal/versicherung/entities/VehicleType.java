package de.starappeal.versicherung.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public record VehicleType(@Id Long id, String name, double factor) {}

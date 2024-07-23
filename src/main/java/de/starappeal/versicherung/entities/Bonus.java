package de.starappeal.versicherung.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
// Maybe userdata?
@Entity
public record Bonus(@Id Long id, Double calculatedBonus) {}

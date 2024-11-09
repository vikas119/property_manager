package com.property_manager.property_manager_be.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String description;
    int proposedPrice;
    long time;
    int actualPrice;

    public Expense(String description, int proposedPrice, long time) {
        this.description = description;
        this.proposedPrice = proposedPrice;
        this.time = time;
    }
}

package com.property_manager.property_manager_be.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
public class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    List<String> names;
    String description;
    Date leaseStart;
    Date leaseEnd;
    int rent;
    int securityDeposit;

    public Lease(List<String> names) {
        this.names = names;
    }
}

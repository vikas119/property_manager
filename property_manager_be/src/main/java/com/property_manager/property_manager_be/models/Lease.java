package com.property_manager.property_manager_be.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    Map<Long, Tenant> tenants;
    String name;
    LocalDate leaseStart;
    LocalDate leaseEnd;
    int rent;
    int securityDeposit;
    @Setter LocalDate dueDate;
    @Setter long dueAmount;

    @OneToMany(mappedBy = "lease", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tenant> tenantList;

    public Lease(
            String name, LocalDate leaseStart, LocalDate leaseEnd, int rent, int securityDeposit) {
        this.name = name;
        this.leaseStart = leaseStart;
        this.leaseEnd = leaseEnd;
        this.rent = rent;
        this.securityDeposit = securityDeposit;
        dueAmount = 0;
    }
}

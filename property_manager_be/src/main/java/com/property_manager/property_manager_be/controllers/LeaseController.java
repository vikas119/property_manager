package com.property_manager.property_manager_be.controllers;

import com.property_manager.property_manager_be.exceptions.LeaseNotFound;
import com.property_manager.property_manager_be.exceptions.TenantNotFound;
import com.property_manager.property_manager_be.models.Lease;
import com.property_manager.property_manager_be.models.Tenant;
import com.property_manager.property_manager_be.repository.LeaseRepository;
import com.property_manager.property_manager_be.repository.TenantRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/v1/lease")
public class LeaseController {
    @Autowired LeaseRepository leaseRepository;
    @Autowired TenantRepository tenantRepository;

    @PostMapping(path = "/add")
    public Lease addLease(
            @RequestBody String name,
            @RequestBody long leaseStart,
            @RequestBody long leaseEnd,
            @RequestBody int rent,
            @RequestBody int securityDeposit) {
        Lease lease = new Lease(name, leaseStart, leaseEnd, rent, securityDeposit);
        leaseRepository.save(lease);
        return lease;
    }

    @PostMapping(path = "/add_tenant")
    public Lease addTenantToLease(@RequestBody int tenantId, int leaseId) {
        Optional<Tenant> tenant = tenantRepository.findById(tenantId);
        Optional<Lease> lease = leaseRepository.findById(leaseId);
        if (tenant.isEmpty()) {
            throw new TenantNotFound();
        }
        if (lease.isEmpty()) {
            throw new LeaseNotFound();
        }
        return lease.get();
    }

    @GetMapping(path = "/get")
    public Iterable<Lease> getAllLease() {
        return leaseRepository.findAll();
    }
}

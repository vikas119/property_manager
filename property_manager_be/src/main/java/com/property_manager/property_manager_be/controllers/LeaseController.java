package com.property_manager.property_manager_be.controllers;

import com.property_manager.property_manager_be.exceptions.LeaseNotFound;
import com.property_manager.property_manager_be.exceptions.TenantNotFound;
import com.property_manager.property_manager_be.exceptions.TenantPresent;
import com.property_manager.property_manager_be.models.Lease;
import com.property_manager.property_manager_be.models.Tenant;
import com.property_manager.property_manager_be.repository.LeaseRepository;
import com.property_manager.property_manager_be.repository.TenantRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/v1/lease")
public class LeaseController {
    @Autowired LeaseRepository leaseRepository;
    @Autowired TenantRepository tenantRepository;

    @PostMapping(path = "/add")
    public Lease addLease(
            @RequestBody String name,
            @RequestBody LocalDate leaseStart,
            @RequestBody LocalDate leaseEnd,
            @RequestBody int rent,
            @RequestBody int securityDeposit) {
        Lease lease = new Lease(name, leaseStart, leaseEnd, rent, securityDeposit);
        leaseRepository.save(lease);
        return lease;
    }

    @PostMapping(path = "/add_tenant")
    public Lease addTenantToLease(@RequestParam long tenantId, @RequestParam long leaseId) {
        Tenant tenant = tenantRepository.findById(tenantId).orElseThrow(TenantNotFound::new);
        Lease lease = leaseRepository.findById(leaseId).orElseThrow(LeaseNotFound::new);
        if (lease.getTenants().containsKey(tenantId)) {
            throw new TenantPresent();
        }
        lease.getTenants().put(tenantId, tenant);
        leaseRepository.save(lease);
        return lease;
    }

    @PostMapping(path = "/remove_tenant")
    public Lease removeTenantFromLease(@RequestParam long tenantId, @RequestParam long leaseId) {
        Lease lease = leaseRepository.findById(leaseId).orElseThrow(LeaseNotFound::new);
        if (!lease.getTenants().containsKey(tenantId)) {
            throw new TenantNotFound();
        }
        lease.getTenants().remove(tenantId);
        leaseRepository.save(lease);
        return lease;
    }

    @GetMapping(path = "/get")
    public Iterable<Lease> getAllLease() {
        return leaseRepository.findAll();
    }

    @GetMapping(path = "/get_lease")
    public Lease getLease(@RequestParam long leaseId) {
        return leaseRepository.findById(leaseId).orElseThrow(LeaseNotFound::new);
    }

    @PutMapping(path = "/modify_due_date")
    public Lease modifyDueDate(@RequestParam long leaseId, @RequestBody LocalDate dueDate) {
        Lease lease = leaseRepository.findById(leaseId).orElseThrow(LeaseNotFound::new);
        lease.setDueDate(dueDate);
        return leaseRepository.findById(leaseId).orElseThrow(LeaseNotFound::new);
    }

    @PutMapping(path = "/collect_rent")
    public Lease collectRent(@RequestParam long leaseId) {
        Lease lease = leaseRepository.findById(leaseId).orElseThrow(LeaseNotFound::new);
        lease.setDueDate(lease.getDueDate().plusMonths(1));
        return lease;
    }
}

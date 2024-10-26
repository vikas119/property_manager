package com.property_manager.property_manager_be.controllers;


import com.property_manager.property_manager_be.models.Lease;
import com.property_manager.property_manager_be.repository.LeaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping(path = "/v1/lease")
public class LeaseController {
    @Autowired
    LeaseRepository leaseRepository;
    @PostMapping(path = "/add")
    public Lease addLease(@RequestParam int propertyId, @RequestBody List<String> names) {
        Lease lease = new Lease(names);
        leaseRepository.save(lease);
        return lease;
    }

    @GetMapping(path = "/get")
    public Iterable<Lease> getAllLease() {
        return leaseRepository.findAll();
    }
}

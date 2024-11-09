package com.property_manager.property_manager_be.controllers;

import com.property_manager.property_manager_be.models.Tenant;
import com.property_manager.property_manager_be.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/v1/tenant")
public class TenantController {
    @Autowired TenantRepository tenantRepository;

    @PostMapping(path = "/add")
    public @ResponseBody Tenant addTenant(@RequestBody String name) {
        Tenant tenant = new Tenant(name);
        tenantRepository.save(tenant);
        return tenant;
    }

    @GetMapping(path = "/get")
    public @ResponseBody Iterable<Tenant> getAllTenant() {
        return tenantRepository.findAll();
    }
}

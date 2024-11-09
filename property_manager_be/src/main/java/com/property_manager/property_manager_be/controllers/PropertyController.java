package com.property_manager.property_manager_be.controllers;

import com.property_manager.property_manager_be.exceptions.LeaseNotFound;
import com.property_manager.property_manager_be.exceptions.LeasePresent;
import com.property_manager.property_manager_be.exceptions.PropertyNotFound;
import com.property_manager.property_manager_be.models.Expense;
import com.property_manager.property_manager_be.models.Lease;
import com.property_manager.property_manager_be.models.Property;
import com.property_manager.property_manager_be.repository.ExpenseRepository;
import com.property_manager.property_manager_be.repository.LeaseRepository;
import com.property_manager.property_manager_be.repository.PropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping(path = "/v1/property")
public class PropertyController {
    @Autowired private PropertyRepository propertyRepository;
    @Autowired private LeaseRepository leaseRepository;
    @Autowired private ExpenseRepository expenseRepository;

    @PostMapping(path = "/add")
    public @ResponseBody Property addProperty(@RequestParam String name) {
        Property property = new Property(name);
        propertyRepository.save(property);
        return property;
    }

    @PostMapping(path = "/add_lease")
    public @ResponseBody Property addLeaseToProperty(
            @RequestParam long propertyId, @RequestParam long leaseId) {
        Lease lease = leaseRepository.findById(leaseId).orElseThrow(LeaseNotFound::new);
        Property property =
                propertyRepository.findById(propertyId).orElseThrow(PropertyNotFound::new);
        if (property.getLeases().containsKey(leaseId)) {
            throw new LeasePresent();
        }
        property.getLeases().put(leaseId, lease);
        propertyRepository.save(property);
        return property;
    }

    @PostMapping(path = "/add_expense")
    public @ResponseBody Property addExpenseToProperty(
            @RequestParam long propertyId,
            @RequestBody String description,
            @RequestBody int proposedPrice,
            @RequestBody long time) {
        Expense expense = new Expense(description, proposedPrice, time);
        Property property =
                propertyRepository.findById(propertyId).orElseThrow(PropertyNotFound::new);
        expenseRepository.save(expense);
        property.getExpenses().put(expense.getId(), expense);
        propertyRepository.save(property);
        return property;
    }

    @GetMapping(path = "/all")
    public @ResponseBody ResponseEntity<Iterable<Property>> getProperties() {
        return new ResponseEntity<>(propertyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/get_lease")
    public @ResponseBody ResponseEntity<Iterable<Lease>> getLeaseProperties(
            @RequestParam long propertyId) {
        Property property =
                propertyRepository.findById(propertyId).orElseThrow(PropertyNotFound::new);
        return new ResponseEntity<>(property.getLeases().values(), HttpStatus.OK);
    }

    @GetMapping(path = "/get_expense")
    public @ResponseBody ResponseEntity<Iterable<Expense>> getExpenseProperties(
            @RequestParam long propertyId) {
        Property property =
                propertyRepository.findById(propertyId).orElseThrow(PropertyNotFound::new);
        return new ResponseEntity<>(property.getExpenses().values(), HttpStatus.OK);
    }
}

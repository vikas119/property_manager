package com.property_manager.property_manager_be.controllers;

import com.property_manager.property_manager_be.models.Property;
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
    @Autowired
    private PropertyRepository propertyRepository;
    @PostMapping(path = "/add")
    public @ResponseBody Property addProperty(@RequestParam String name) {
        Property property = new Property(name);
        propertyRepository.save(property);
        return property;
    }

    @GetMapping(path = "/get_all")
    public @ResponseBody ResponseEntity<Iterable<Property> >getProperties() {
        return new ResponseEntity<>(propertyRepository.findAll(), HttpStatus.OK);
    }
}

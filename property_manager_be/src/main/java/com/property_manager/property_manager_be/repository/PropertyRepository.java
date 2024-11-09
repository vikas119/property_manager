package com.property_manager.property_manager_be.repository;

import com.property_manager.property_manager_be.models.Property;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<Property, Long> {}

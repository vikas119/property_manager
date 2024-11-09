package com.property_manager.property_manager_be.repository;

import com.property_manager.property_manager_be.models.Lease;
import org.springframework.data.repository.CrudRepository;

public interface LeaseRepository extends CrudRepository<Lease, Integer> {}

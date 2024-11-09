package com.property_manager.property_manager_be.repository;

import com.property_manager.property_manager_be.models.Tenant;
import org.springframework.data.repository.CrudRepository;

public interface TenantRepository extends CrudRepository<Tenant, Long> {}

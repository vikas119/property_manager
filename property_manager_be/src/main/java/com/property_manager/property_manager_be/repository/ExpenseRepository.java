package com.property_manager.property_manager_be.repository;

import com.property_manager.property_manager_be.models.Expense;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {}

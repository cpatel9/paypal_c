package com.paypal.bfs.test.employeeserv.api.impl.repository;

import com.paypal.bfs.test.employeeserv.api.impl.repository.entities.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
/**
 * 
 * @author cpate
 *
 */
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {
}

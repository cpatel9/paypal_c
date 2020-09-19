package com.paypal.bfs.test.employeeserv.api.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.impl.repository.exception.DuplicateEmployeeException;
import com.paypal.bfs.test.employeeserv.api.impl.repository.exception.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.api.impl.service.EmployeeService;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.api.impl.validator.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 
 * @author cpate
 *  Implementation class for employee resource.
 *
 */

@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public ResponseEntity<Employee> employeeGetById(Integer id) {
    	
        try {
            if (id == null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            Employee employee = employeeService.getEmployee(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (EmployeeNotFoundException employeeNotFound) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Create Employee Entity
     */
    @Override
    public ResponseEntity<Employee> createEmployee(@Valid Employee employee) {
        try {
            if (!EmployeeValidator.isValid(employee)) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            Employee savedEmployee = employeeService.createEmployee(employee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        } catch (DuplicateEmployeeException ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//end ResponseEntity
}

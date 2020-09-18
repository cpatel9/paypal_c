package com.paypal.bfs.test.employeeserv.api.impl.repository.exception;

public class EmployeeNotFoundException extends Exception {

    public EmployeeNotFoundException(int id) {
        super("Employee details not found for " + id);
    }
}

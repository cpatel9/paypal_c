package com.paypal.bfs.test.employeeserv.api.impl.repository.exception;

public class DuplicateEmployeeException extends Exception {

    public DuplicateEmployeeException(int id) {
        super("Employee with employee id " + id + " exists");
    }
}

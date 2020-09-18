package com.paypal.bfs.test.employeeserv.api.impl.validator;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.apache.commons.lang.StringUtils;

public class EmployeeValidator {

    public static boolean isValid(Employee employee) {
        if(employee.getId() == null || employee.getId() == 0
                || employee.getDateOfBirth() == null || employee.getAddress() == null) {
            return false;
        }
        return StringUtils.isNotBlank(employee.getFirstName()) &&
                StringUtils.isNotBlank(employee.getLastName()) &&
                StringUtils.isNotBlank(employee.getAddress().getLine1()) &&
                StringUtils.isNotBlank(employee.getAddress().getCity()) &&
                StringUtils.isNotBlank(employee.getAddress().getState()) &&
                StringUtils.isNotBlank(employee.getAddress().getCountry());
    }
}

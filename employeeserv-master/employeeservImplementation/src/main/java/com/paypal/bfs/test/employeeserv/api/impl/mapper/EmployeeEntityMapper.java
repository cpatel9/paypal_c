package com.paypal.bfs.test.employeeserv.api.impl.mapper;

import com.paypal.bfs.test.employeeserv.api.impl.repository.entities.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class EmployeeEntityMapper implements Function<Employee, EmployeeEntity> {

    @Override
    public EmployeeEntity apply(Employee employee) {
        Address address = employee.getAddress();
        return EmployeeEntity
                .builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .dateOfBirth(employee.getDateOfBirth())
                .line1(address.getLine1())
                .line2(address.getLine2())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .zipCode(address.getZipCode())
                .build();
    }
}

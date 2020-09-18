package com.paypal.bfs.test.employeeserv.api.impl.mapper;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.api.impl.repository.entities.EmployeeEntity;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class EmployeeMapper implements Function<EmployeeEntity, Employee> {

    @Override
    public Employee apply(EmployeeEntity entity) {

        Employee employee = new Employee();
        employee.setId(entity.getId());
        employee.setFirstName(entity.getFirstName());
        employee.setLastName(entity.getLastName());
        employee.setDateOfBirth(entity.getDateOfBirth());

        Address address = new Address();
        address.setLine1(entity.getLine1());
        address.setLine2(entity.getLine2());
        address.setCity(entity.getCity());
        address.setState(entity.getState());
        address.setCountry(entity.getCountry());
        address.setZipCode(entity.getZipCode());

        employee.setAddress(address);
        return employee;
    }
}

package com.paypal.bfs.test.employeeserv.api.impl.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.paypal.bfs.test.employeeserv.api.impl.mapper.EmployeeEntityMapper;
import com.paypal.bfs.test.employeeserv.api.impl.mapper.EmployeeMapper;
import com.paypal.bfs.test.employeeserv.api.impl.repository.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.api.impl.repository.entities.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.api.impl.repository.exception.DuplicateEmployeeException;
import com.paypal.bfs.test.employeeserv.api.impl.repository.exception.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
/**
 * 
 * @author cpate
 *
 */
@Service
public class EmployeeService {

    private EmployeeMapper employeeMapper;

    private EmployeeRepository employeeRepository;

    private EmployeeEntityMapper employeeEntityMapper;

    public EmployeeService(EmployeeMapper employeeMapper, EmployeeRepository employeeRepository, EmployeeEntityMapper employeeEntityMapper) {
        this.employeeEntityMapper = employeeEntityMapper;
        this.employeeMapper = employeeMapper;
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployee(int id) throws EmployeeNotFoundException {
        Optional<EmployeeEntity> entity = employeeRepository.findById(id);
        return entity.map(employeeMapper).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Employee createEmployee(Employee employee) throws DuplicateEmployeeException {

        Optional<EmployeeEntity> existingEntity = employeeRepository.findById(employee.getId());
        if (existingEntity.isPresent()) {
            throw new DuplicateEmployeeException(employee.getId());
        }
        EmployeeEntity entity = employeeRepository.save(employeeEntityMapper.apply(employee));
        return employeeMapper.apply(entity);
    }
}

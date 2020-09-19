package com.paypal.bfs.test.employeeserv.api.impl.repository.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
 * 
 * @author cpate
 *
 */
@Data
@Entity
@Builder
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    private Integer id;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String line1;

    private String line2;

    private String city;

    private String state;

    private String country;

    private Integer zipCode;
}

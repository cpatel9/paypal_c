package com.paypal.bfs.test.employeeserv.api;

import com.paypal.bfs.test.employeeserv.api.impl.service.EmployeeService;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes={ EmployeeservApplication.class })
public class EmployeeResourceTest extends AbstractTest {
    MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @MockBean
    private EmployeeService employeeMockService;

    @Test
    public void createEmployee() throws Exception {
        String uri = "/v1/bfs/employees";

        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("FirstName");
        employee.setLastName("LastName");
        employee.setDateOfBirth(new Date());

        Address address = new Address();
        address.setLine1("Line 1");
        address.setLine2("Line 2");
        address.setCity("City");
        address.setState("State");
        address.setCountry("Country");
        address.setZipCode(12345);
        employee.setAddress(address);


        String inputJson = super.mapToJson(employee);
        final Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setFirstName("FirstName");
        employee1.setLastName("LastName");
        employee1.setDateOfBirth(employee.getDateOfBirth());

        Address address1 = new Address();
        address1.setLine1("Line 1");
        address1.setLine2("Line 2");
        address1.setCity("City");
        address1.setState("State");
        address1.setCountry("Country");
        address1.setZipCode(12345);
        employee1.setAddress(address1);
        when(employeeMockService.createEmployee(any())).thenReturn(employee1);
        MvcResult mvcResult = this.mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        Employee savedEmployee = super.mapFromJson(content, Employee.class);
        assertNotNull(savedEmployee.getId());
        assertEquals(employee.getFirstName(), savedEmployee.getFirstName());
        assertEquals(employee.getLastName(), savedEmployee.getLastName());
        assertEquals(employee.getDateOfBirth(), savedEmployee.getDateOfBirth());

        Address savedAddress = savedEmployee.getAddress();
        assertEquals(address.getLine1(), savedAddress.getLine1());
        assertEquals(address.getLine2(), savedAddress.getLine2());
        assertEquals(address.getCity(), savedAddress.getCity());
        assertEquals(address.getState(), savedAddress.getState());
        assertEquals(address.getCountry(), savedAddress.getCountry());
        assertEquals(address.getZipCode(), savedAddress.getZipCode());
    }


    @Test
    public void getEmployee() throws Exception {
        String uri = "/v1/bfs/employees/1";

        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("FirstName");
        employee.setLastName("LastName");
        employee.setDateOfBirth(new Date());

        Address address = new Address();
        address.setLine1("Line 1");
        address.setLine2("Line 2");
        address.setCity("City");
        address.setState("State");
        address.setCountry("Country");
        address.setZipCode(12345);
        employee.setAddress(address);
        when( employeeMockService.getEmployee(1)).thenReturn(employee);

        MvcResult mvcResult = this.mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();


        Employee savedEmployee = super.mapFromJson(content, Employee.class);
        assertNotNull(savedEmployee.getId());
        assertEquals(employee.getFirstName(), savedEmployee.getFirstName());
        assertEquals(employee.getLastName(), savedEmployee.getLastName());
        assertEquals(employee.getDateOfBirth(), savedEmployee.getDateOfBirth());

        Address savedAddress = savedEmployee.getAddress();
        assertEquals(address.getLine1(), savedAddress.getLine1());
        assertEquals(address.getLine2(), savedAddress.getLine2());
        assertEquals(address.getCity(), savedAddress.getCity());
        assertEquals(address.getState(), savedAddress.getState());
        assertEquals(address.getCountry(), savedAddress.getCountry());
        assertEquals(address.getZipCode(), savedAddress.getZipCode());
    }
}

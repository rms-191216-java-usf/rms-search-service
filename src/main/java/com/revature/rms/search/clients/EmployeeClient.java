package com.revature.rms.search.clients;




import com.revature.rms.search.entites.employee.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/employee")
@FeignClient(name = "employee-service")

public interface EmployeeClient {

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllEmployee();

    @GetMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getEmployeeById(@PathVariable String id);

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getEmployeeByID();

}

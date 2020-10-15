package com.revature.rms.search.clients;

import com.revature.rms.search.entites.employee.Employee;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequestMapping("/employees")
@FeignClient(name = "employee-service")
public interface EmployeeClient {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllEmployee();

    @GetMapping(value = "/id/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getEmployeeById(@PathVariable int id);

    @GetMapping(value = "/ids/{ids}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getEmployeesByIds(@PathVariable List<Integer> ids);

    @GetMapping(value = "/owners/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllEmployeeByOwner(@PathVariable("id") int id);

}

package com.revature.rms.search.clients;

import com.revature.rms.search.entites.employee.Employee;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RequestMapping("/employee")
@FeignClient(name = "employee-service")
public interface EmployeeClient {

    @GetMapping(value = "/employees" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllEmployee();

    @GetMapping(value = "/id/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getEmployeeById(@PathVariable int id);

    @GetMapping(value = "/getallbyid", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllById(@RequestParam List<Integer> ids);

}
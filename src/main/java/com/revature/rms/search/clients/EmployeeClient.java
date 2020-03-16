package com.revature.rms.search.clients;

import com.revature.rms.search.entites.employee.Employee;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

<<<<<<< HEAD
import com.revature.rms.search.entites.employee.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

=======

>>>>>>> 9cb7dd483ce07d33040cddf826694d04791263d4
@RequestMapping("/employee")
@FeignClient(name = "employee-service")
public interface EmployeeClient {

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllEmployee();

    @GetMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
<<<<<<< HEAD
    public Employee getEmployeeById(@PathVariable String id);
=======
    public Employee getEmployeeById(@PathVariable int id);
>>>>>>> 9cb7dd483ce07d33040cddf826694d04791263d4

    @GetMapping(value = "/getallbyid", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllById(@RequestBody List<Integer> ids);

<<<<<<< HEAD
}
=======
}
>>>>>>> 9cb7dd483ce07d33040cddf826694d04791263d4

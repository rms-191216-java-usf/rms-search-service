package com.revature.rms.search.clients;

import com.revature.rms.search.entites.employee.AppUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
@FeignClient(name = "auth-service")
public interface AuthClient {

    @GetMapping(value = "/getUserById", produces = MediaType.APPLICATION_JSON_VALUE)
    public AppUser getUserById(@PathVariable int id);

}

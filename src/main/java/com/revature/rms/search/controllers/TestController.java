package com.revature.rms.search.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String test(){
        return "/hello this is Ben Burke's Test!";

    }


}

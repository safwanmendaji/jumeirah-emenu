package com.jumeirah.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillingController {

    @GetMapping("/test")
    public String test(){
        return "Test Work";
    }
}

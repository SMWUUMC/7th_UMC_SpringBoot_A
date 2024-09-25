package com.example._th_umc_study;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class test_controller {

    @GetMapping("hello")
    public String test() {
        return "Hello World";
    }
}

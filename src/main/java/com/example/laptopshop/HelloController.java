package com.example.laptopshop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/user")
    public String user() {
            return "user";
    }

    @GetMapping("/admin")
    public String admin() {
            return "admin";
    }

    @GetMapping("/")
    public String index() {
            return "Long bắt đầu học code hihi test";
    }


    

}

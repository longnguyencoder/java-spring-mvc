package com.example.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.laptopshop.service.UserService;


@Controller
public class UserController {
        private UserService userService;

    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/test")
    public String getHomePage() {
         String test = userService.handleHello();
        return "eric.html";
    }
}

// // @RestController
// // public class UserController {
// //     private UserService userService;

    
// //     public UserController(UserService userService) {
// //         this.userService = userService;
// //     }


// //     @GetMapping("/")
// //     public String getHomePage() {

// //         return userService.handleHello();
// //     }
// }
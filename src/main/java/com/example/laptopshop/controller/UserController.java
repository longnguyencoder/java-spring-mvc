package com.example.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String getHomePage(Model model) {
         String test = userService.handleHello();
         model.addAttribute("eric", test);
        return "hello";
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
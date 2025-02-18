package com.example.laptopshop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemController {
    @GetMapping("/product/{id}")
    public String getProductPage(Model Model, @PathVariable long id) {
        return "client/product/detailProduct";
    }

}

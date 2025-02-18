package com.example.laptopshop.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.laptopshop.domain.Product;
import com.example.laptopshop.domain.User;
import com.example.laptopshop.service.ProductService;
import com.example.laptopshop.service.UploadService;
import com.example.laptopshop.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(ProductService productService, UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;

    }

    @GetMapping("/admin/product")
    public String getProduct(Model model) {
        List<Product> product = this.productService.fetchProduct();
        model.addAttribute("products", product);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }
    // lưu sản phẩm

    @PostMapping(value = "/admin/product/create")
    public String createProductPage(Model model, @ModelAttribute("newProduct") @Valid Product longhoccode,
            BindingResult bindingResult, @RequestParam("saveFile") MultipartFile file) {

        // // validation
        // List<FieldError> errors = bindingResult.getFieldErrors();
        // for (FieldError error : errors) {
        // System.out.println(error.getField() + " - " + error.getDefaultMessage());
        // }
        // validate
        if (bindingResult.hasErrors()) {
            return "/admin/product/create";
        }
        // upload image
        String image = this.uploadService.handSaveUploadFile(file, "product");
        longhoccode.setImage(image);
        this.productService.createProduct(longhoccode);
        return "redirect:/admin/product";
    }

}

package com.example.laptopshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

        if (bindingResult.hasErrors()) {
            return "/admin/product/create";
        }
        // upload image
        String image = this.uploadService.handSaveUploadFile(file, "product");
        longhoccode.setImage(image);
        this.productService.createProduct(longhoccode);
        return "redirect:/admin/product";
    }

    // xóa sản phẩm
    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProductPage(Model model, @PathVariable long id) {
        // User user = new User();
        // user.setId(id);
        model.addAttribute("id", id);
        model.addAttribute("newProduct", new Product());

        return "admin/product/delete";
    }

    @PostMapping("/admin/product/delete")
    public String postDeleteUser(Model model, @ModelAttribute("newUser") User longhoccode) {
        this.productService.deleteProduct(longhoccode.getId());
        return "redirect:/admin/product";
    }

    // xem chi tiết sản phẩm
    @GetMapping("/admin/product/{id}")
    public String getProductDetailPage(Model model, @PathVariable long id) {
        Product product = this.productService.fetchProductById(id).get();
        model.addAttribute("product", product);
        model.addAttribute("id", id);

        return "admin/product/detail";
    }

    // update sản phẩm

    @GetMapping("/admin/product/update/{id}") // get
    public String getUpdateProductPage(Model model, @PathVariable long id) {
        Optional<Product> currenProduct = this.productService.fetchProductById(id);
        model.addAttribute("newProduct", currenProduct.get());
        return "admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String getUpdateProduct(@ModelAttribute("newProduct") @Valid Product longhoccode,
            BindingResult bindingResult, @RequestParam("saveFile") MultipartFile file) {

        // validate
        if (bindingResult.hasErrors()) {
            return "admin/product/update";
        }
        Product cProduct = this.productService.fetchProductById(longhoccode.getId()).get();
        if (cProduct != null) {
            if (!file.isEmpty()) {
                String img = this.uploadService.handSaveUploadFile(file, "product");
                cProduct.setImage(img);
            }
            cProduct.setName(longhoccode.getName());
            cProduct.setPrice(longhoccode.getPrice());
            cProduct.setQuantity(longhoccode.getQuantity());
            cProduct.setDetailDesc(longhoccode.getDetailDesc());
            cProduct.setShortDesc(longhoccode.getShortDesc());
            cProduct.setFactory(longhoccode.getFactory());
            cProduct.setTarget(longhoccode.getTarget());

            this.productService.createProduct(cProduct);
        }

        return "redirect:/admin/product";
    }

}

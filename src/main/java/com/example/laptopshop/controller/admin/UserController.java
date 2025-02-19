package com.example.laptopshop.controller.admin;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.laptopshop.domain.User;
import com.example.laptopshop.repository.UserRepository;
import com.example.laptopshop.service.UploadService;
import com.example.laptopshop.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {
    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UploadService uploadService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> arrUsers = this.userService.getAllUserByEmail("longnguyen16@gmail.com");
        System.out.println(arrUsers);
        String test = userService.handleHello();
        model.addAttribute("eric", test);
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users1", users);
        return "admin/user/show";
    }

    @RequestMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    // lưu người dùng
    @PostMapping(value = "/admin/user/create")
    public String createUserPage(Model model, @ModelAttribute("newUser") @Valid User longhoccode,
            BindingResult bindingResult, @RequestParam("saveFile") MultipartFile file) {

        // validation
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getField() + " - " + error.getDefaultMessage());
        }
        // validate
        if (bindingResult.hasErrors()) {
            return "/admin/user/create";
        }

        System.out.println("run here" + longhoccode);
        String avatar = this.uploadService.handSaveUploadFile(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(longhoccode.getPassword());

        longhoccode.setAvatar(avatar);
        longhoccode.setPassword(hashPassword);
        longhoccode.setRole(this.userService.getRoleByName(longhoccode.getRole().getName()));

        this.userService.handSaveUser(longhoccode);
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/update/{id}") // get
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User currenUser = this.userService.getUserById(id);

        model.addAttribute("newUser", currenUser);
        return "admin/user/update";
    }

    @PostMapping("/admin/user/update")
    public String getUpdateUser(Model model, @ModelAttribute("newUser") User longhoccode) {
        User currenUser = this.userService.getUserById(longhoccode.getId());

        // check điều kiện
        if (currenUser != null) {
            currenUser.setAddress(longhoccode.getAddress());
            currenUser.setFullname(longhoccode.getFullname());
            currenUser.setPhone(longhoccode.getPhone());
            this.userService.handSaveUser(currenUser);
        }
        model.addAttribute("newUser", currenUser);
        return "redirect:/admin/user";
    }

    // xem chi tiết thông tin người dùng
    @RequestMapping("/admin/user/{id}") // get
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);

        return "admin/user/detail";
    }

    // xóa người dùng
    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        // User user = new User();
        // user.setId(id);
        model.addAttribute("newUser", new User());
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String postDeleteUser(Model model, @ModelAttribute("newUser") User longhoccode) {
        this.userService.deleteUser(longhoccode.getId());
        return "redirect:/admin/user";
    }
}

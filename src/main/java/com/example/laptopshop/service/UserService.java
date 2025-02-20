package com.example.laptopshop.service;

import java.util.List;

import org.eclipse.tags.shaded.org.apache.regexp.recompile;
import org.springframework.stereotype.Service;

import com.example.laptopshop.domain.Role;
import com.example.laptopshop.domain.User;
import com.example.laptopshop.domain.dto.RegisterDTO;

import com.example.laptopshop.repository.RoleRepository;
import com.example.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public String handleHello() {
        return "hello service";
    }

    // lấy ra tất cả người dùng
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    // lấy ra người dùng theo email
    public List<User> getAllUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    // lấy ra người dùng theo email
    public User getUserById(long id) {
        return this.userRepository.findById(id);
    }

    public User handSaveUser(User user) {
        User demo = this.userRepository.save(user);

        return demo;
    }

    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    public Role getRoleByName(String name) {
        return this.roleRepository.findByName(name);
    }

    // mapper này dùng để map lastname và firts bên register thành fullname của user
    public User registerDTOtoUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setFullname(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        return user;
    }

    // check email đã tồn tại hay chưa
    public boolean checkEmailExist(String email) {
        return this.userRepository.existsByEmail(email);
    }
}

package org.rbrtwlz.issuetracking.controller;

import org.rbrtwlz.issuetracking.entity.Author;
import org.rbrtwlz.issuetracking.entity.User;
import org.rbrtwlz.issuetracking.form.UserForm;
import org.rbrtwlz.issuetracking.service.UserService;
import org.rbrtwlz.issuetracking.user.UserRole;
import org.rbrtwlz.issuetracking.repository.AuthorRepository;
import org.rbrtwlz.issuetracking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String viewHomePage() {
        return "index";
    }
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepository.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserForm());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(UserForm userForm) {
        this.userService.createNewUser(userForm, UserRole.USER);
        UserForm admin = new UserForm("admin@gmx.de", "pwd", "Admin", "Admin");
        this.userService.createNewUser(admin, UserRole.ADMIN);
        UserForm support = new UserForm("support@gmx.de", "pwd", "Support", "Support");
        this.userService.createNewUser(support, UserRole.SUPPORT);
        return "register_success";
    }
}
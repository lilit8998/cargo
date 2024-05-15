package com.example.cargo.endpoint;

import com.example.cargo.entity.User;
import com.example.cargo.entity.enums.UserRole;
import com.example.cargo.security.SpringUser;
import com.example.cargo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String userRegisterPage(@RequestParam(value = "msg", required = false) String msg, ModelMap modelMap) {
        if (msg != null && !msg.isEmpty()) {
            modelMap.addAttribute("msg", msg);
        }
        modelMap.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String userRegister(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }
        if (userService.isEmailExists(user.getEmail())) {
            return "redirect:/user/registration?msg=Email already in use";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/user/registration?msg=User registered";
    }

    @GetMapping("/loginPage")
    public String loginPage(@AuthenticationPrincipal SpringUser springUser) {
        if (springUser == null) {
            return "loginPage";
        }
        return "redirect:/";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal SpringUser springUser) {
        User user = springUser.getUser();
        if (user.getUserRole() == UserRole.USER) {
            return "redirect:/";
        } else if (user.getUserRole() == UserRole.ADMIN) {
            return "redirect:/admin/home";
        }
        return "redirect:/";
    }

    @GetMapping("/admin/home")
    public String adminHomePage() {
        return "admin/adminHome";
    }
}


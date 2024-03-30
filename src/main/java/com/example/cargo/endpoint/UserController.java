package com.example.cargo.endpoint;

import com.example.cargo.entity.User;
import com.example.cargo.entity.enums.UserRole;
import com.example.cargo.repository.UserRepository;
import com.example.cargo.security.SpringUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("user/registration")
    public String userRegisterPage(@RequestParam(value = "msg", required = false) String msg, ModelMap modelMap) {
        if (msg != null && msg.isEmpty()) {
            modelMap.addAttribute("msg", msg);
        }
        modelMap.addAttribute("user",new User());
        return "registration";
    }

    @PostMapping("/user/registration")
    public String userRegister(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()){
            return "registration";
        }
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        if (byEmail.isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } else {
            return "redirect:/user/registration?msg=Email already in use";
        }
        return "redirect:/user/registration?msg=User registered";
    }

    @GetMapping("/loginPage")
    public String loginPage(@AuthenticationPrincipal SpringUser springUser){
        if (springUser == null){
            return "loginPage";
        }
        return "redirect:/";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal SpringUser springUser){
        User user = springUser.getUser();
        if (user.getUserRole() == UserRole.USER) {
            return "redirect:/";
        } else if (user.getUserRole() == UserRole.ADMIN) {
            return "redirect:/admin/home";
        }
        return "redirect:/";
    }

    @GetMapping("/admin/home")
    public String adminHomePage(){
        return "admin/adminHome";
    }
}

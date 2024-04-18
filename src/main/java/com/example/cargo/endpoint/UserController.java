package com.example.cargo.endpoint;

import com.example.cargo.entity.User;
import com.example.cargo.entity.enums.UserRole;
import com.example.cargo.repository.UserRepository;
import com.example.cargo.security.SpringUser;
import com.example.cargo.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/account")
    public String userAccount(@AuthenticationPrincipal SpringUser user, ModelMap model) {
        model.addAttribute("user", user);
        return "user/account";
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
            result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            return "registration";
        }
        try {
            if (userService.isEmailExists(user.getEmail())) {
                return "redirect:/user/registration?msg=Email already in use";
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
            return "redirect:/user/registration?msg=User registered";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/user/registration?msg=An unexpected error occurred";
        }
    }


    @RequestMapping(value = "/updateUser")
    public String up(ModelMap map, @RequestParam(name = "message", required = false) String message, @ModelAttribute("add") User ser1,
                     @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            map.addAttribute("message", message != null ? message : "");
            User user = ((SpringUser) userDetails).getUser();
            map.addAttribute("current", userRepository.findById(user.getId()));
        }
        return "userUpdate";
    }

    @GetMapping("/loginPage")
    public String loginPage(@AuthenticationPrincipal SpringUser springUser, Error e) {
        if (springUser == null) {
            return "loginPage";
        } else {
            return "redirect:/account";
        }
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal SpringUser springUser) {
        if (springUser != null && springUser.getUser() != null) {
            User user = springUser.getUser();
            if (user.getUserRole() == UserRole.USER) {
                return "redirect:/account";
            } else if (user.getUserRole() == UserRole.ADMIN) {
                return "redirect:/admin/home";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model, HttpSession session) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findById(id));
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userRepository.delete(user);
            session.invalidate();
            return "redirect:/";
        } else {

            return "redirect:/news";
        }
    }
    @GetMapping("/admin/home")
    public String adminHomePage() {
        return "admin/adminHome";
    }
}
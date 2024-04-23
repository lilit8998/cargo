package com.example.cargo.endpoint;

import com.example.cargo.dto.UserDto;
import com.example.cargo.entity.User;
import com.example.cargo.entity.enums.UserRole;
import com.example.cargo.repository.UserRepository;
import com.example.cargo.security.SpringUser;
import com.example.cargo.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    private final   PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;


    public UserController(UserService userService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
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
    public String userRegister(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "registration";
        }
        if (userService.isEmailExists(userDto.getEmail())) {
            return "redirect:/user/registration?msg=Email already in use";
        }

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setPassword(encodedPassword);
        user.setDob(userDto.getDob());
        user.setUserRole(UserRole.USER);

        try {
            userService.save(user);
            model.addAttribute("registeredUser", user);
            return "redirect:/user/registration?msg=User registered";
        } catch (Exception e) {
            return "redirect:/user/registration?msg=Error occurred during registration";
        }
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
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") long id, ModelMap model) {
        Optional<Object> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
            return "user/update";
        } else {
            return "/";
        }
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User updatedUser, ModelMap model) {
        try {
            userService.updateUser(updatedUser);
            return "redirect:/user/account?msg=User updated";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "user/update";
        }
    }


    @GetMapping("/admin/home")
    public String adminHomePage() {
        return "admin/adminHome";
    }
}
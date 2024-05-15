package com.example.cargo.endpoint;

import com.example.cargo.dto.BranchSaveDto;
import com.example.cargo.dto.CityResponseDto;
import com.example.cargo.dto.CountryResponseDto;
import com.example.cargo.entity.Branch;
import com.example.cargo.security.SpringBranch;
import com.example.cargo.service.BranchService;
import com.example.cargo.service.CityService;
import com.example.cargo.service.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/branch")
public class BranchController {
    private final BranchService branchService;
    private final PasswordEncoder passwordEncoder;
    private final CountryService countryService;
    private final CityService cityService;

    @GetMapping("/registrationBranch")
    public String branchRegisterPage(ModelMap modelMap) {
        List<CountryResponseDto> countryServiceAll = countryService.getAll();
        List<CityResponseDto> cityServiceAll = cityService.getAll();
        modelMap.addAttribute("countries", countryServiceAll);
        modelMap.addAttribute("cities", cityServiceAll);

        return "registrationBranch";
    }

    @PostMapping("/registrationBranch")
    public String registrationBranch(
            @Valid @ModelAttribute("branch") BranchSaveDto branch,
            BindingResult result,
            @RequestParam(required = false) Integer country,
            @RequestParam(required = false) Integer city,
            Model model
    ) {
        if (result.hasErrors()) {
            return "registrationBranch";
        }
        branch.setPassword(passwordEncoder.encode(branch.getPassword()));
        if (country != null && city != null) {
            branchService.save(branch, city, country);
        } else {
            model.addAttribute("error", "Please fill all the required fields");
        }
        return "redirect:/branch/registrationBranch";
    }

    @GetMapping("/loginBranch")
    public String loginPage(@AuthenticationPrincipal SpringBranch springBranch) {
        if (springBranch == null) {
            return "loginBranch";
        }
        return "redirect:/loginBranch";
    }
//
//    @GetMapping("/loginSuccess")
//    public String loginSuccess(@AuthenticationPrincipal SpringBranch springBranch) {
//        Branch branch = springBranch.getBranch();
//        return "redirect:/branch/home";
//    }
//
//    @GetMapping("/branch/home")
//    public String branchHomePage() {
//        return "/branch/branchHome";
//    }
}
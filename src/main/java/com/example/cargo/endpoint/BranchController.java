package com.example.cargo.endpoint;

import com.example.cargo.dto.BranchSaveDto;
import com.example.cargo.dto.CityResponseDto;
import com.example.cargo.dto.CountryResponseDto;
import com.example.cargo.entity.Product;
import com.example.cargo.service.BranchService;
import com.example.cargo.service.CityService;
import com.example.cargo.service.CountryService;
import com.example.cargo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final ProductService productService;
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
    @GetMapping("/user/account")
    public String accountPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails == null) {
            return "redirect:/loginPage";
        }

        boolean hasBranch = branchService.existsByUserId(1L);
        model.addAttribute("hasBranch", hasBranch);

        return "branch/account";
    }

    @GetMapping("/products")
    public String branchProducts(Model model) {
        List<Product> productsReceived = productService.getReceivedProducts();
        List<Product> productsReleased = productService.getReleasedProducts();

        model.addAttribute("productsReceived", productsReceived);
        model.addAttribute("productsReleased", productsReleased);

        return "branch/products";
    }

}
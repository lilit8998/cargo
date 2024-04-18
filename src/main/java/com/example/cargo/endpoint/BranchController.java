package com.example.cargo.endpoint;

import com.example.cargo.entity.Branch;
import com.example.cargo.service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/branch")
public class BranchController {
    private final BranchService branchService;
    @GetMapping("/register")
    public String branchRegisterPage(){
        return "registrationBranch";
    }

    @PostMapping("/register")
    public String registrationBranch(@Valid @ModelAttribute("branch") Branch branch, BindingResult result, Model model){
        if (result.hasErrors()){
            return "registrationBranch";
        }
        branchService.branchRegister(branch);
        return "redirect:/branch/register";
    }
}

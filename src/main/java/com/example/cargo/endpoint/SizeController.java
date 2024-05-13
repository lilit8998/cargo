package com.example.cargo.endpoint;

import com.example.cargo.repository.SizeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SizeController {
    private SizeRepository sizeRepository;

    @GetMapping("/sizes")
    public String size(Model model) {
        model.addAttribute("sizes",sizeRepository.findAll());
        return "calculate";
    }




}

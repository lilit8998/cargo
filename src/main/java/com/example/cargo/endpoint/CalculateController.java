package com.example.cargo.endpoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalculateController {

    @GetMapping("/calculate")
    public String calculate() {
        return "calculate";
    }
}

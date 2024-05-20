package com.example.cargo.endpoint;

import com.example.cargo.dto.CalculateDto;
import com.example.cargo.service.CalculateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CalculateController {

    private final CalculateService calculateService;

    public CalculateController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @GetMapping("/calculate")
    public String showCalculateForm(Model model) {
        model.addAttribute("calculate", new CalculateDto());
        return "calculate";
    }

    @PostMapping("/calculate")
    public String calculateDistances(@ModelAttribute("calculate") CalculateDto calculateDto,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            return "calculate";
        }
        if (calculateDto.getCityFrom().isEmpty() || calculateDto.getCityTo().isEmpty() || calculateDto.getParcelSize().isEmpty()) {
            model.addAttribute("errorMessage", "Form fields cannot be empty.");
            return "calculate";
        }
        System.out.println("calculate distance");

        String[] cityFromCoords = calculateDto.getCityFrom().split(",");
        String[] cityToCoords = calculateDto.getCityTo().split(",");

        double lat1 = Double.parseDouble(cityFromCoords[0]);
        double lon1 = Double.parseDouble(cityFromCoords[1]);
        double lat2 = Double.parseDouble(cityToCoords[0]);
        double lon2 = Double.parseDouble(cityToCoords[1]);

        double calculatedDistance = calculateService.calculateDistance(lat1, lon1, lat2, lon2);

        double parcelSize = calculateService.parseParcelSize(calculateDto.getParcelSize());
        double basePrice = calculateService.calculatePrice(calculatedDistance, parcelSize);

        model.addAttribute("totalPrice", (int) basePrice);

        return "calculate";
    }

}

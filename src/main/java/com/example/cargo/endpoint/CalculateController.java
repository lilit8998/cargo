package com.example.cargo.endpoint;

import com.example.cargo.dto.CalculateDto;
import com.example.cargo.service.CalculateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
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

        if (areFieldsEmpty(calculateDto)) {
            model.addAttribute("errorMessage", "Form fields cannot be empty.");
            return "calculate";
        }

        String[] cityFromCoords = calculateDto.getCityFrom().split(",");
        String[] cityToCoords = calculateDto.getCityTo().split(",");

        if (cityFromCoords.length != 2 || cityToCoords.length != 2) {
            model.addAttribute("errorMessage", "Invalid coordinates format.");
            return "calculate";
        }

        try {
            double[] cityFrom = parseCoordinates(cityFromCoords);
            double[] cityTo = parseCoordinates(cityToCoords);

            double lat1 = cityFrom[0];
            double lon1 = cityFrom[1];
            double lat2 = cityTo[0];
            double lon2 = cityTo[1];

            double calculatedDistance = calculateService.calculateDistance(lat1, lon1, lat2, lon2);

            double parcelSize = calculateService.parseParcelSize(calculateDto.getParcelSize());
            double basePrice = calculateService.calculatePrice(calculatedDistance, parcelSize);

            model.addAttribute("totalPrice", (int) basePrice);

        } catch (NumberFormatException e) {
            model.addAttribute("errorMessage", "Invalid coordinate format.");
            return "calculate";
        }

        log.info("calculate distance finished");
        return "calculate";
    }

    private boolean areFieldsEmpty(CalculateDto calculateDto) {
        return calculateDto.getCityFrom().isEmpty() ||
                calculateDto.getCityTo().isEmpty() ||
                calculateDto.getParcelSize().isEmpty();
    }

    private double[] parseCoordinates(String[] coords) throws NumberFormatException {
        double[] parsedCoords = new double[2];
        parsedCoords[0] = Double.parseDouble(coords[0]);
        parsedCoords[1] = Double.parseDouble(coords[1]);
        return parsedCoords;
    }

}

package com.example.cargo.endpoint;

import com.example.cargo.dto.CalculateDto;
import com.example.cargo.entity.Size;
import com.example.cargo.repository.SizeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class CalculateController {

    private final SizeRepository sizeRepository;

    public CalculateController(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    @GetMapping("/calculate")
    public String showCalculateForm(Model model) {
        model.addAttribute("calculate", new CalculateDto());
        return "calculate";
    }

    // Radius of the Earth in kilometers
    private static final double EARTH_RADIUS = 6371;

    @PostMapping("/calculate")
    public String calculateDistance(@ModelAttribute("calculate") CalculateDto calculateDto,
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

        double calculatedDistance = calculateDistance(lat1, lon1, lat2, lon2);

        String parcelSize = String.valueOf(calculateDto.getParcelSize());
        double size = parseParcelSize(parcelSize);
        String[] parts = parcelSize.split(" ");
        String dimensions = parts[0];
        String weightString = parts[1];

        String[] dimensionValues = dimensions.split("x");
        int width = Integer.parseInt(dimensionValues[0]);
        int height = Integer.parseInt(dimensionValues[1]);
        int depth = Integer.parseInt(dimensionValues[2]);

        String weightStringWithoutUnit = weightString.replaceAll("[^0-9]", "");
        int weight = Integer.parseInt(weightStringWithoutUnit);

        double basePrice = calculatePrice(calculatedDistance, size);

        Optional<Size> sizeEntity = sizeRepository.findByWidthAndHeightAndDepthAndWeight(width, height, depth, weight);
        if (sizeEntity.isPresent()) {
            double sizePrice = sizeEntity.get().getPrice();
            double totalPrice = basePrice + sizePrice;
            model.addAttribute("totalPrice", (int) totalPrice);
            model.addAttribute("sizePrice", sizePrice);
        } else {
            model.addAttribute("totalPrice", (int) basePrice);
            model.addAttribute("sizePrice", 0);
        }

        return "calculate";
    }


    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {

        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        double a = Math.pow(Math.sin(deltaLat / 2), 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.pow(Math.sin(deltaLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    private double calculatePrice(double distance, Double size) {

        double basePrice = distance * 25;
        double sizePrice = 0.0;

        if (size != null) {
            if (size.equals(1.0)) {
                sizePrice = 100;
            } else if (size.equals(2.0)) {
                sizePrice = 150;
            } else if (size.equals(30.0)) {
                sizePrice = 200;
            }
        }

        double totalPrice = basePrice + sizePrice;
        return totalPrice;
    }

    private double parseParcelSize(String parcelSize) {

        String numericPart = parcelSize.replaceAll("[^\\d.]", "");

        return Double.parseDouble(numericPart);
    }

}

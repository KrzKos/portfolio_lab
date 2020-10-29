package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.dto.CategoryDTO;
import pl.coderslab.charity.model.dto.DonationAddDTO;
import pl.coderslab.charity.model.dto.InstitutionDTO;
import pl.coderslab.charity.model.service.CategoryService;
import pl.coderslab.charity.model.service.DonationService;
import pl.coderslab.charity.model.service.InstitutionService;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/donation")
public class DonationController {
    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final DonationService donationService;

    @ModelAttribute("categories")
    private List<CategoryDTO> getAllCategories() {
        return categoryService.findAllCategories();
    }
    @ModelAttribute("institutions")
    private List<InstitutionDTO> getAllInstitutions() {
        return institutionService.findAll();
    }

    @GetMapping
    public String prepareForm(Model model){
        model.addAttribute("donation", new DonationAddDTO());
        return "form";
    }

    @PostMapping
    public String showDonation(@ModelAttribute("donation") DonationAddDTO donationAddDTO) {
        donationService.create(donationAddDTO);
        return "form-confirmation";
    }
}

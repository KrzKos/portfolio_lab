package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.dto.CategoryDTO;
import pl.coderslab.charity.model.dto.InstitutionDTO;
import pl.coderslab.charity.model.service.CategoryService;
import pl.coderslab.charity.model.service.DonationService;
import pl.coderslab.charity.model.service.InstitutionService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/") @Controller
public class HomeController {
    private final InstitutionService institutionService;
    private final CategoryService categoryService;
    private final DonationService donationService;

    @ModelAttribute("institutions")
    public List<InstitutionDTO> getAllInstitutions() {
        return institutionService.findAll();
    }
    @ModelAttribute("sumQuantity")
    public Integer sumAllQuantity() {
        return donationService.sumAllQuantity();
    }
    @ModelAttribute("countDonation")
    public Integer countDonation(){
        return donationService.countDonation();
    }
    @GetMapping
    public String getInstitutions(Model model){

        model.addAttribute("category",categoryService.findByName("Kat1"));
        //model.addAttribute("inst", getAllInstitutions());
        return "index";
    }
}

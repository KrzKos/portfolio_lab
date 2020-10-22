package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.dto.CategoryDTO;
import pl.coderslab.charity.model.dto.DonationAddDTO;
import pl.coderslab.charity.model.service.CategoryService;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/donation")
public class DonationController {
    private final CategoryService categoryService;

    @ModelAttribute("categories")
    private List<CategoryDTO> showAllCategories() {
        return categoryService.findAllCategories();
    }

    @GetMapping
    public String prepareForm(Model model){
        model.addAttribute("donation", new DonationAddDTO());
        return "form";
    }
}

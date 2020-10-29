package pl.coderslab.charity.model.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.dto.InstitutionDTO;
import pl.coderslab.charity.model.service.InstitutionService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/institution")
public class InstitutionController {

    private final InstitutionService institutionService;

    @GetMapping("list")
    public String getAllInstitutions(Model model) {
        model.addAttribute("institutions", institutionService.findAll());
        return "institution/list";
    }

    @GetMapping("/add")
    public String addInstitution(Model model) {
        model.addAttribute("institution", new InstitutionDTO());
        return "institution/form";
    }

    @PostMapping("/add")
    public String saveInstitution(@Valid InstitutionDTO institution, BindingResult result) {
        if (result.hasErrors()) {
            return "institution/form";
        }
        institutionService.create(institution);
        return "redirect:/admin/institution/list";
    }

    @GetMapping("/{id}/edit")
    public String editInstitution(@PathVariable long id, Model model) {
        InstitutionDTO institution = institutionService.findById(id);
        if (institution == null) {
            return "error";
        }
        model.addAttribute("institution", institution);
        return "institution/edit";
    }

    @PostMapping("/{id}/edit")
    public String saveEditedInstitution(@PathVariable long id, @Valid InstitutionDTO institution, BindingResult result) {
        if (institution.getId() != id) {
            return "error";
        }
        if (result.hasErrors()) {
            return "institution/edit";
        }
        institutionService.update(institution);
        return "redirect:/admin/institution/list";
    }

    @GetMapping("/{id}/delete")
    public String deleteInstitution(@PathVariable long id, Model model) {
        InstitutionDTO institution = institutionService.findById(id);
        if (institution == null) {
            return "error";
        }
        model.addAttribute("institution", institution);
        return "institution/delete";
    }

    @PostMapping("/{id}/delete}")
    public String deleteInstitutionById(@PathVariable long id){
        InstitutionDTO institution = institutionService.findById(id);
        institutionService.delete(institution);
        return "redirect:/admin/institution/list";
    }


}

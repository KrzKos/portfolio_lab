package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.dto.UserCreateDTO;
import pl.coderslab.charity.model.service.UserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;

    @GetMapping
    public String prepareForm(Model model){
        model.addAttribute("user", new UserCreateDTO());
        return "register";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") @Valid UserCreateDTO userCreateDTO, BindingResult result) {
        if(result.hasErrors()) {
            return "register";
        }
        if(!userService.create(userCreateDTO)){
            return "register";
        }
        return "redirect:/login";
    }
}

package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.auth.AuthenticationFacade;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final AuthenticationFacade authenticationFacade;

    @GetMapping
    @ResponseBody
    public String showUserDetail() {
        return "Hello " + authenticationFacade.getAuthentication().getPrincipal();
    }

}

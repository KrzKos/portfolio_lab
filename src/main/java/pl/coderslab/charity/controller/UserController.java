package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.auth.AuthenticationFacade;
import pl.coderslab.charity.model.entity.User;
import pl.coderslab.charity.model.service.UserService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthenticationFacade authenticationFacade;

    @GetMapping
    @ResponseBody
    public String showUserDetail() {

        return "Hello " + authenticationFacade.getAuthentication().getPrincipal();

    }

    @GetMapping("/add")
    @ResponseBody
    public User addUser() {
        User user = new User();
        user.setUsername("admin2");
        user.setPassword(passwordEncoder.encode("pass"));
        user.setActive(true);
        user.setRole("ADMIN");
        userService.create(user);
        return user;
    }

}

package dev.andreafay.my_copy_paste.controller;

import dev.andreafay.my_copy_paste.model.Link;
import dev.andreafay.my_copy_paste.model.User;
import dev.andreafay.my_copy_paste.service.LinkService;
import dev.andreafay.my_copy_paste.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    LinkService linkService;
    @Autowired
    UserService userService;

    @Value("$EMAIL_USER1")
    private String email1;
    @Value("$PASSWORD_USER1")
    private String password1;
    @Value("$EMAIL_USER2")
    private String email2;
    @Value("$PASSWORD_USER2")
    private String password2;

    @GetMapping("/")
    public String homepage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        if (email == null || email.equals("anonymousUser")) {
            return "login";
        }

        Optional<User> userOptional = userService.getUserByEmail(email);

        if (userOptional.isEmpty()) {
            return "login";
        }

        User user = userOptional.get();
        model.addAttribute("user", user);

        List<Link> links = linkService.getUserLinksByEmail(user.getEmail());
        model.addAttribute("links", links);

        return "index";
    }

    @GetMapping("/login")
    public String login(){
        userService.initRootUsers(email1, password1, email2, password2);
        return "login";
    }
}
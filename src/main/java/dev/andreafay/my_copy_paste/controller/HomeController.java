package dev.andreafay.my_copy_paste.controller;

import dev.andreafay.my_copy_paste.model.Link;
import dev.andreafay.my_copy_paste.model.User;
import dev.andreafay.my_copy_paste.service.LinkService;
import dev.andreafay.my_copy_paste.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/")
    public String homepage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Optional<User> userOptional = userService.getUserByEmail(email);
        userOptional.ifPresent(user -> model.addAttribute("user", user));

        User user = userOptional.orElseThrow();

        List<Link> links = linkService.getUserLinksByEmail(user.getEmail());
        model.addAttribute("links", links);

        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
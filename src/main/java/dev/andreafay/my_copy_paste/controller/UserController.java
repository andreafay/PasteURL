package dev.andreafay.my_copy_paste.controller;

import dev.andreafay.my_copy_paste.model.User;
import dev.andreafay.my_copy_paste.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public List<User> getUsers(){
        userService.saveUser("andre13.fay@gmail.com", "Andre", "password");
        userService.saveUser("test@gmail.com", "Test", "password");
        return userService.getUsers();
    }
}

package dev.andreafay.my_copy_paste.service.impl;

import dev.andreafay.my_copy_paste.model.User;
import dev.andreafay.my_copy_paste.repository.UserRepository;
import dev.andreafay.my_copy_paste.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(String email, String name, String password) {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        userRepository.save(user);
    }
}

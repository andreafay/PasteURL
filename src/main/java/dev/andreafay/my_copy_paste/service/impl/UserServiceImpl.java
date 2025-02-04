package dev.andreafay.my_copy_paste.service.impl;

import dev.andreafay.my_copy_paste.model.User;
import dev.andreafay.my_copy_paste.repository.UserRepository;
import dev.andreafay.my_copy_paste.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(String email, String name, String password) {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findById(email);
    }

    @Override
    public void initRootUsers(String email1, String password1, String email2, String password2) {
        if(getUserByEmail(email1).isEmpty()){
            saveUser(email1, "Andre", password1);
        }
        if(getUserByEmail(email2).isEmpty()){
            saveUser(email2, "Liisu", password2);
        }
    }
}

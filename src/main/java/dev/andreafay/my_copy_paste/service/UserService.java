package dev.andreafay.my_copy_paste.service;

import dev.andreafay.my_copy_paste.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUsers();
    void saveUser(String email, String name, String password);
    Optional<User> getUserByEmail(String email);
}

package dev.andreafay.my_copy_paste.repository;

import dev.andreafay.my_copy_paste.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}

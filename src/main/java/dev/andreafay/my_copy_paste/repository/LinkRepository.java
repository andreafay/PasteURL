package dev.andreafay.my_copy_paste.repository;

import dev.andreafay.my_copy_paste.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Long> {
    List<Link> findByUserEmail(String email);
}

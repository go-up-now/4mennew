package com.poly.repository;

import com.poly.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUsername(String userName);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}

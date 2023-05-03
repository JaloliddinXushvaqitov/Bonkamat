package com.example.bankomat.repository;

import com.example.bankomat.entity.karta.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.util.Optional;

@Repository
public interface UserRepozitary extends JpaRepository<Users,Integer> {
    Optional<Users> findByEmail(@Email String email);
  Optional<Users> findByPasswordRaqam(String passwordRaqam);
    boolean existsByPasswordRaqam(String passwordRaqam);
}

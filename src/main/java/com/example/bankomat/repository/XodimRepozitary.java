package com.example.bankomat.repository;

import com.example.bankomat.entity.Xodim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface XodimRepozitary extends JpaRepository<Xodim,Integer> {
    Optional<Xodim>findByUsername(String username);
    Optional<Xodim> findByUsernameAndEmailkod(String username, String emailkod);
}

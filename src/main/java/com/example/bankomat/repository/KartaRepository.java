package com.example.bankomat.repository;

import com.example.bankomat.entity.karta.Karta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KartaRepository extends JpaRepository<Karta,Integer> {
    boolean existsByBankIdAndUsersId(Integer bank_id, Integer users_id);
}

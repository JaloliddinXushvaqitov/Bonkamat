package com.example.bankomat.repository;

import com.example.bankomat.entity.template.Komissiya;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KomissiyaRepozitary extends JpaRepository<Komissiya,Integer> {
    boolean existsByBank_Nomi(String bank_nomi);
}

package com.example.bankomat.repository;

import com.example.bankomat.entity.KartaTuri;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KartaTuriRepository extends JpaRepository<KartaTuri,Integer> {
    boolean existsByNomi(String nomi);
}

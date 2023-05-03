package com.example.bankomat.repository;

import com.example.bankomat.entity.bankomat.Kpyura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KpyuraRepozitary extends JpaRepository<Kpyura,Integer> {
}

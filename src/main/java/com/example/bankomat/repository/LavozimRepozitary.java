package com.example.bankomat.repository;

import com.example.bankomat.entity.Lavozim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LavozimRepozitary extends JpaRepository<Lavozim,Integer> {
}

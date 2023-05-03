package com.example.bankomat.repository;

import com.example.bankomat.entity.karta.KartaMuddati;
import org.springframework.data.jpa.repository.JpaRepository;



public interface KartaMuddatiRepozitary extends JpaRepository<KartaMuddati,Integer> {
boolean existsByBankIdAndKartaTuriIdAndKartaMuddat(Integer bank_id, Integer kartaTuri_id, Integer kartaMuddat);
}

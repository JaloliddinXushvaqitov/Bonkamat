package com.example.bankomat.service;

import com.example.bankomat.entity.Bank;
import com.example.bankomat.entity.KartaTuri;
import com.example.bankomat.entity.karta.KartaMuddati;
import com.example.bankomat.payload.ApiRespons;
import com.example.bankomat.payload.KartaMuddatDTO;
import com.example.bankomat.repository.BankRepository;
import com.example.bankomat.repository.KartaMuddatiRepozitary;
import com.example.bankomat.repository.KartaTuriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KartaMuddatiService {
    @Autowired
    KartaMuddatiRepozitary kartaMuddatiRepozitary;

    @Autowired
    KartaTuriRepository kartaTuriRepository;

    @Autowired
    BankRepository bankRepository;
    public ApiRespons addMuddat(Integer bankId, Integer turId, KartaMuddatDTO kartaMuddatDTO) {
        final boolean b = kartaMuddatiRepozitary.existsByBankIdAndKartaTuriIdAndKartaMuddat(bankId, turId, kartaMuddatDTO.getKartaMuddat());
        if(!b){
            KartaMuddati kartaMuddat=new KartaMuddati();
            kartaMuddat.setKartaMuddat(kartaMuddatDTO.getKartaMuddat());
            final Optional<Bank> byId = bankRepository.findById(bankId);
            if(byId.isPresent()){
                kartaMuddat.setBank(byId.get());
                final Optional<KartaTuri> byId1 = kartaTuriRepository.findById(turId);
                if(byId1.isPresent()){
                kartaMuddat.setKartaTuri(byId1.get());
                kartaMuddatiRepozitary.save(kartaMuddat);
                return new ApiRespons("karta muddati muaffaqiyatli saqlandi",true);
                }
                return new ApiRespons("bunday karta turi id mavjud emas11",false);
            }
            return new ApiRespons("bunday bank id mavjud emass!!",false);
        }
        return new ApiRespons("bunday malumotlar mavjud boshqa malumot kiriting",false);
    }
}

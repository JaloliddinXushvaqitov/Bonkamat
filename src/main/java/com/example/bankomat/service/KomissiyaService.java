package com.example.bankomat.service;

import com.example.bankomat.entity.template.Komissiya;
import com.example.bankomat.payload.ApiRespons;
import com.example.bankomat.payload.KomissiyaDTO;
import com.example.bankomat.repository.BankRepository;
import com.example.bankomat.repository.KomissiyaRepozitary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KomissiyaService {
    @Autowired
    KomissiyaRepozitary komissiyaRepozitary;
    @Autowired
    BankRepository bankRepository;

    public ApiRespons komissiyaADD(KomissiyaDTO komissiyaDTO, String banknomi) {
        final boolean b = komissiyaRepozitary.existsByBank_Nomi(banknomi);
        if(b){
            Komissiya komissiya=new Komissiya(
                    komissiyaDTO.getFoizMiqdori(),
                    bankRepository.findById(komissiyaDTO.getBankID()).get()
            );
            komissiyaRepozitary.save(komissiya);
            return new ApiRespons("Malumot saqlandi",false);
        }
        return new ApiRespons("Bunday bank bazada mavjud emas!!!",false);
    }
}

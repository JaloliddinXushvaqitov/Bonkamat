package com.example.bankomat.service;

import com.example.bankomat.entity.bankomat.Kpyura;
import com.example.bankomat.payload.ApiRespons;
import com.example.bankomat.repository.KpyuraRepozitary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KpyuraService {
    @Autowired
    KpyuraRepozitary kpyuraRepozitary;
    public ApiRespons kpyuraadd(Kpyura kpyura) {
        Kpyura kpyura1=new Kpyura(
                kpyura.getMing_200(),
                kpyura.getMing_100(),
                kpyura.getMing_50(),
                kpyura.getMing_20(),
                kpyura.getMing_10(),
                kpyura.getMing_5()
        );
        kpyuraRepozitary.save(kpyura1);
        return new ApiRespons("malumot saqlandi",true);
    }
}

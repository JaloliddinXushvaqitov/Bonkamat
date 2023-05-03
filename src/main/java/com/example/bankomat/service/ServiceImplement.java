package com.example.bankomat.service;

import com.example.bankomat.entity.Bank;
import com.example.bankomat.entity.KartaTuri;
import com.example.bankomat.entity.Manzil;
import com.example.bankomat.payload.ApiRespons;
import com.example.bankomat.payload.BankDto;
import com.example.bankomat.payload.KartaDto;
import com.example.bankomat.payload.UserDto;
import com.example.bankomat.repository.BankRepository;
import com.example.bankomat.repository.KartaTuriRepository;
import com.example.bankomat.repository.ManzilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceImplement implements LoyihalashService{

    @Autowired
    BankRepository bankRepository;
    @Autowired
    ManzilRepository manzilRepository;
    @Autowired
    KartaTuriRepository kartaTuriRepository;
    @Override
    public ApiRespons addbank(BankDto bankDto) {
        boolean b = bankRepository.existsByNomi(bankDto.getNomi());
        boolean b1 = manzilRepository.existsByViloyatAndTumanAndKucha(bankDto.getViloyat(), bankDto.getTuman(), bankDto.getKucha());
        if (!b1 && !b){
            Manzil manzil = new Manzil();
            manzil.setViloyat(bankDto.getViloyat());
            manzil.setTuman(bankDto.getTuman());
            manzil.setKucha(bankDto.getKucha());
            manzilRepository.save(manzil);
            Bank bank = new Bank(
                    bankDto.getNomi(),
                    manzil
            );
            bankRepository.save(bank);
            return new ApiRespons("Bank ma'lumotlari muvaffaqiyatli saqlandi!",true);
        }
        return new ApiRespons("Kechirasiz bunday manzil bo'sh emas yoki bunday nomli bank mavjud",false);
    }

    @Override
    public ApiRespons addkartaturi(KartaTuri kartaTuri) {
        boolean b = kartaTuriRepository.existsByNomi(kartaTuri.getNomi());
        if (!b){
            KartaTuri kartaTuri1 = new KartaTuri(
                    kartaTuri.getNomi(),
                    kartaTuri.getBoshlanishKodi()
            );
            kartaTuriRepository.save(kartaTuri1);
            return new ApiRespons("Karta turi muvaffaqiatli saqlandi!",true);
        }
        return new ApiRespons("Bunday karta turi mavjud!",false);
    }
}

package com.example.bankomat.service;

import com.example.bankomat.entity.Bank;
import com.example.bankomat.entity.KartaTuri;
import com.example.bankomat.entity.Manzil;
import com.example.bankomat.entity.enums.MijozStatus;
import com.example.bankomat.entity.karta.Karta;
import com.example.bankomat.entity.karta.KartaMuddati;
import com.example.bankomat.entity.karta.Users;
import com.example.bankomat.payload.ApiRespons;
import com.example.bankomat.payload.KartaDto;
import com.example.bankomat.payload.KartaQoshishDTO;
import com.example.bankomat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Service
public class KartaService {
    @Autowired
    KartaRepository kartaRepository;
    @Autowired
    BankRepository bankRepository;
    @Autowired
    UserRepozitary userRepozitary;
    @Autowired
    KartaMuddatiRepozitary kartaMuddatiRepozitary;
    @Autowired
    KartaTuriRepository kartaTuriRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ManzilRepository manzilRepository;

    public ApiRespons kartaqoshish(KartaDto kartaDto, Integer bankID, Integer userID) {
        final boolean b = kartaRepository.existsByBankIdAndUsersId(bankID, userID);
        if (b) {
            final Optional<Bank> byId = bankRepository.findById(bankID);
            if (byId.isPresent()) {
                final Optional<Users> byId1 = userRepozitary.findById(userID);
                if (byId1.isPresent()) {
                    Karta karta = new Karta();
                    karta.setKartaRaqam(kartaDto.getKartaRaqam());
                    karta.setPinKod(kartaDto.getPinKod());
                   // karta.setBalance(kartaDto.getBalance());
                    karta.setBank(byId.get());
                    karta.setUsers(byId1.get());
                   // karta.setKartaMuddati(kartaMuddatiRepozitary.findById(kartaDto.getKartaMuddatiId()).get());
                    karta.setKartaTuri(kartaTuriRepository.findById(kartaDto.getKartaTuriid()).get());
                    kartaRepository.save(karta);
                    return new ApiRespons("Karta malumotgaa qoshildi", true);
                }
                return new ApiRespons("Bunday user id mavjud emas", false);
            }
            return new ApiRespons("Bunday bank bazada mavjud emas iltimos boshqa bankni tanlang", false);
        }
        return new ApiRespons("Bunday malumotlar topilmadi ilstimos boshqa malumot kiriting!!", false);
    }

    public ApiRespons kartaADD(KartaQoshishDTO kartaQoshishDTO, Integer kartaTuriId) {
        if (kartaQoshishDTO.getMijozStatus().equals(MijozStatus.ADD) && !userRepozitary.existsByPasswordRaqam(kartaQoshishDTO.getUsers().getPasswordRaqam())) {
            Manzil manzil = new Manzil(
                    kartaQoshishDTO.getManzil().getViloyat(),
                    kartaQoshishDTO.getManzil().getTuman(),
                    kartaQoshishDTO.getManzil().getKucha()
            );
            Users users = new Users(
                    kartaQoshishDTO.getUsers().getFish(),
                    kartaQoshishDTO.getUsers().getEmail(),
                    kartaQoshishDTO.getUsers().getTelRaqam(),
                    kartaQoshishDTO.getUsers().getPasswordRaqam(),
                    manzil
            );
            Karta karta = new Karta();
            int kod=new Random().nextInt(99999);
            int kod1=new Random().nextInt(99999);
            final Optional<KartaMuddati> byId = kartaMuddatiRepozitary.findById(kartaTuriId);
            karta.setKartaRaqam(byId.get().getKartaTuri().getBoshlanishKodi()+kod+kod1);
            karta.setPinKod(kartaQoshishDTO.getPinKod());
            karta.setMuddat(LocalDate.now().plusYears(byId.get().getKartaMuddat()));
            final Optional<Bank> byId1 = bankRepository.findById(byId.get().getBank().getId());
            karta.setBank(byId1.get());
            karta.setUsers(users);
            final Optional<KartaTuri> byId2 = kartaTuriRepository.findById(byId.get().getKartaTuri().getId());
            karta.setKartaTuri(byId2.get());
            manzilRepository.save(manzil);
            userRepozitary.save(users);
            kartaRepository.save(karta);
            return new ApiRespons("karta yangi yaratildi",true);
        }
        if (kartaQoshishDTO.getMijozStatus().equals(MijozStatus.EDIT) && userRepozitary.existsByPasswordRaqam(kartaQoshishDTO.getUsers().getPasswordRaqam())) {
            final Optional<Users> byPasswordRaqam = userRepozitary.findByPasswordRaqam(kartaQoshishDTO.getUsers().getPasswordRaqam());
            final Users users = byPasswordRaqam.get();
            Karta karta = new Karta();
            int kod=new Random().nextInt(99999);
            int kod1=new Random().nextInt(99999);
            final Optional<KartaMuddati> byId = kartaMuddatiRepozitary.findById(kartaTuriId);
            karta.setKartaRaqam(byId.get().getKartaTuri().getBoshlanishKodi()+kod+kod1);
            karta.setPinKod(kartaQoshishDTO.getPinKod());
            karta.setMuddat(LocalDate.now().plusYears(byId.get().getKartaMuddat()));
            final Optional<Bank> byId1 = bankRepository.findById(byId.get().getBank().getId());
            karta.setBank(byId1.get());
            karta.setUsers(users);
//            final Optional<KartaTuri> byId2 = kartaTuriRepository.findById(byId.get().getKartaTuri().getId());
//            karta.setKartaTuri(byId2.get());
            karta.setKartaTuri(kartaTuriRepository.findById(kartaQoshishDTO.getKartaTuriId()).get());
            kartaRepository.save(karta);
            return new ApiRespons("karta yangi yaratildi",true);
        }
            return new ApiRespons("Karta ro`yxatdan o`tgan",false);
    }
//    public ApiRespons kartaEDIT(Integer kartaMuddatiId, KartaQoshishDTO kartaQoshishDTO) {
//
//    }
}

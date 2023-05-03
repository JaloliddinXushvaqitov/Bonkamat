package com.example.bankomat.controller;
import com.example.bankomat.entity.karta.Users;
import com.example.bankomat.payload.ApiRespons;
import com.example.bankomat.payload.KartaDto;
import com.example.bankomat.payload.KartaQoshishDTO;
import com.example.bankomat.payload.PasswordRaqamDto;
import com.example.bankomat.repository.UserRepozitary;
import com.example.bankomat.service.KartaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/karta")
public class KartaController {

    @Autowired
    KartaService kartaService;
    @Autowired
    UserRepozitary userRepozitary;

    @PostMapping("/kartaQoshish/{bankID}/{userID}")
    public HttpEntity<?> Joylash(@RequestBody KartaDto kartaDto, @PathVariable Integer bankID,@PathVariable Integer userID){
        ApiRespons apiRespons = kartaService.kartaqoshish(kartaDto,bankID,userID);
        return ResponseEntity.status(apiRespons.isHolat()?200:208).body(apiRespons.getXabar());
    }

    @PostMapping("/TekshirishKarta")
    public HttpEntity<?>TekshirishKarta(@RequestBody PasswordRaqamDto passwordRaqamDto){
        final Optional<Users> byPasswordRaqam = userRepozitary.findByPasswordRaqam(passwordRaqamDto.getPasswordRaqam());
        if(byPasswordRaqam.isPresent()){
            return ResponseEntity.status(200).body(byPasswordRaqam);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mijoz  royxatdan otmagan!!");
    }
    @PostMapping("/kartaADD/{kartaTuriId}")
    private HttpEntity<?>kartaADD(@RequestBody KartaQoshishDTO kartaQoshishDTO, @PathVariable Integer kartaTuriId){
        ApiRespons apiRespons=kartaService.kartaADD(kartaQoshishDTO,kartaTuriId);
        return ResponseEntity.status(apiRespons.isHolat()?200:208).body(apiRespons.getXabar());
    }
//    @PutMapping("/kartaEDIT/{kartaMuddatiId}")
//    private HttpEntity<?>kartaEDIT(@PathVariable Integer kartaMuddatiId,@RequestBody KartaQoshishDTO kartaQoshishDTO ){
//        ApiRespons apiRespons=kartaService.kartaEDIT(kartaMuddatiId,kartaQoshishDTO);
//        return ResponseEntity.status(apiRespons.isHolat()?200:208).body(apiRespons.getXabar());
//    }
}

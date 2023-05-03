package com.example.bankomat.controller;

import com.example.bankomat.payload.ApiRespons;
import com.example.bankomat.payload.KartaMuddatDTO;
import com.example.bankomat.service.KartaMuddatiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kartaMuddati")
public class KartaMuddatoController {
    @Autowired
    KartaMuddatiService kartaMuddatiService;
    @PostMapping("/addMuddat/{bankId},/{turId}")
    private HttpEntity<?>addMuddat(@PathVariable Integer bankId, @PathVariable Integer turId, @RequestBody KartaMuddatDTO kartaMuddatDTO){
        ApiRespons apiRespons=kartaMuddatiService.addMuddat(bankId,turId,kartaMuddatDTO);
        return ResponseEntity.status(apiRespons.isHolat()?200:208).body(apiRespons.getXabar());
    }
}

package com.example.bankomat.controller;

import com.example.bankomat.payload.ApiRespons;
import com.example.bankomat.payload.KomissiyaDTO;
import com.example.bankomat.service.KomissiyaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/komissiya")
public class KomissiyaController {
    @Autowired
    KomissiyaService komissiyaService;
    @PostMapping("/komissiyaADD")
    private HttpEntity<?>komisiisiyaadd(@RequestBody KomissiyaDTO komissiyaDTO, @PathVariable String banknomi){
        ApiRespons apiRespons=komissiyaService.komissiyaADD(komissiyaDTO,banknomi);
        return ResponseEntity.status(apiRespons.isHolat()?200:208).body(apiRespons.getXabar());
    }
}

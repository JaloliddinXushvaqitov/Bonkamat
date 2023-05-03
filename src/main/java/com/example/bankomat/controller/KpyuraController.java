package com.example.bankomat.controller;

import com.example.bankomat.entity.bankomat.Kpyura;
import com.example.bankomat.entity.template.Abstract;
import com.example.bankomat.payload.ApiRespons;
import com.example.bankomat.service.KpyuraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kpyuralar")
public class KpyuraController extends Abstract {
    @Autowired
    KpyuraService kpyuraService;
    @PostMapping("/kpyuraADD")
    public HttpEntity<?>kpyuraadd(@RequestBody Kpyura kpyura){
        ApiRespons apiRespons=kpyuraService.kpyuraadd(kpyura);
        return ResponseEntity.status(apiRespons.isHolat()?200:208).body(apiRespons.getXabar());
    }

}

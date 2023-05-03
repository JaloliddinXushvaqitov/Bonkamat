package com.example.bankomat.controller;

import com.example.bankomat.payload.ApiRespons;
import com.example.bankomat.payload.AuthDTO;
import com.example.bankomat.payload.XodimDto;
import com.example.bankomat.service.XodimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/xodimQoshish")
public class XodimController {
    @Autowired
    XodimService xodimService;
    @PostMapping("/xodimadd/{username}")
    private HttpEntity<?>qoshish(@RequestBody XodimDto xodimDto, @PathVariable String username){
        ApiRespons apiRespons=xodimService.xodimadd(xodimDto,username);
       return ResponseEntity.status(apiRespons.isHolat()?200:208).body(apiRespons.getXabar());
    }
@GetMapping("/tasdiqlash")
    private HttpEntity<?>tasdiqlash(@RequestParam String username,@RequestParam String emailcode){
        ApiRespons apiRespons=xodimService.tasdiqlash(username,emailcode);
        return ResponseEntity.status(apiRespons.isHolat()?200:208).body(apiRespons.getXabar());
}
@PostMapping("/login")
    private HttpEntity<?>login(AuthDTO authDTO){
        ApiRespons apiRespons=xodimService.login(authDTO);
        return ResponseEntity.status(apiRespons.isHolat()?200:208).body(apiRespons.getXabar());
}
}

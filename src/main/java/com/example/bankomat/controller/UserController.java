package com.example.bankomat.controller;

import com.example.bankomat.payload.ApiRespons;
import com.example.bankomat.payload.UserDto;
import com.example.bankomat.service.LoyihalashService;
import com.example.bankomat.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usersCrud")
public class UserController {

    @Autowired
    UsersService usersService;

    @PostMapping("/addUsers/{email}")
    private HttpEntity<?> addUsers(@RequestBody UserDto userDto, @PathVariable String email){
        ApiRespons apiRespons=usersService.addUsers(userDto,email);
        return ResponseEntity.status(apiRespons.isHolat()?200:208).body(apiRespons.getXabar());
    }
}

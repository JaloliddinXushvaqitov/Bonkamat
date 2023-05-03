package com.example.bankomat.service;

import com.example.bankomat.entity.Manzil;
import com.example.bankomat.entity.karta.Users;
import com.example.bankomat.payload.ApiRespons;
import com.example.bankomat.payload.UserDto;
import com.example.bankomat.repository.ManzilRepository;
import com.example.bankomat.repository.UserRepozitary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public class UsersService  {
    @Autowired
    UserRepozitary userRepozitary;

    @Autowired
    ManzilRepository manzilRepository;

    public ApiRespons addUsers(UserDto userDto, String email) {
        final Optional<Users> byEmail = userRepozitary.findByEmail(email);
        if (!byEmail.isPresent()) {
            Users users = new Users();
            users.setEmail(userDto.getEmail());
            users.setFish(userDto.getFish());
            users.setTelRaqam(userDto.getTelRaqam());
            users.setPasswordRaqam(userDto.getPasswordRaqam());
            Manzil manzil1=new Manzil();
            manzil1.setViloyat(userDto.getViloyat());
            manzil1.setTuman(userDto.getTuman());
            manzil1.setKucha(userDto.getKucha());
            manzilRepository.save(manzil1);
            users.setManzil(manzil1);
            userRepozitary.save(users);
            return new ApiRespons("malumot saqlani!!",true);
        }
        return new ApiRespons("bunday email bazada mavjud",false);
    }

}

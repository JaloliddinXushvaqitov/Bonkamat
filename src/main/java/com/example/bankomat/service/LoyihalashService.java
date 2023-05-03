package com.example.bankomat.service;

import com.example.bankomat.entity.KartaTuri;
import com.example.bankomat.payload.ApiRespons;
import com.example.bankomat.payload.BankDto;
import com.example.bankomat.payload.KartaDto;
import com.example.bankomat.payload.UserDto;

public interface LoyihalashService {
    ApiRespons addbank(BankDto bankDto);

    ApiRespons addkartaturi(KartaTuri kartaTuri);
}

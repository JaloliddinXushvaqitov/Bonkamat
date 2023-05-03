package com.example.bankomat.payload;

import lombok.Data;

@Data
public class KartaDto {
    private String kartaRaqam;
    private Integer pinKod;
    private Float balance;
    private Integer kartaMuddatiId;
    private Integer kartaTuriid;
   // private UserDto passwordRaqam;
}

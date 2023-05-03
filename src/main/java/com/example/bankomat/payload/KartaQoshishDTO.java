package com.example.bankomat.payload;

import com.example.bankomat.entity.KartaTuri;
import com.example.bankomat.entity.Manzil;
import com.example.bankomat.entity.enums.MijozStatus;
import com.example.bankomat.entity.karta.Users;
import lombok.Data;

@Data
public class KartaQoshishDTO {
    private Integer pinKod;
    private Users users;
    private Integer kartaTuriId;
    private Manzil manzil;
    private MijozStatus mijozStatus;
}

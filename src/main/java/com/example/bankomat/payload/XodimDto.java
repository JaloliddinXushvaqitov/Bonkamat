package com.example.bankomat.payload;

import com.example.bankomat.entity.Lavozim;
import com.example.bankomat.entity.Manzil;
import lombok.Data;

@Data
public class XodimDto {
    private String fish;
    private String username;
    private String password;
    private Lavozim lavozim;
    private Manzil manzil;
}

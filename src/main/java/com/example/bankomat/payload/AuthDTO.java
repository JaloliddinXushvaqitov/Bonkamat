package com.example.bankomat.payload;

import lombok.Data;

@Data
public class AuthDTO {
    private String username;
    private String password;
}

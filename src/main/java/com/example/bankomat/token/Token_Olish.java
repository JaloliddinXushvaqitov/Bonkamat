package com.example.bankomat.token;

import com.example.bankomat.entity.Lavozim;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class Token_Olish {

    String kalit="kalit1234";
    public String Token_Olish(String username, Lavozim lavozim) {
        long vaqt=60*60*1000*24;
        Date yashash=new Date(System.currentTimeMillis()+vaqt);
        String token= Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(yashash)
                .claim("Lavozim",lavozim.getLavozimNomi())
                .signWith(SignatureAlgorithm.HS512,kalit)
                .compact();
        return token;
    }
    public String dishifrovka(String token) {
        String username = Jwts
                .parser()
                .setSigningKey(kalit)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return username;
    }
}

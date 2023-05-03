package com.example.bankomat.entity.template;

import com.example.bankomat.entity.Xodim;
import com.example.bankomat.entity.enums.Huquqlar;
import com.example.bankomat.entity.Lavozim;
import com.example.bankomat.repository.LavozimRepozitary;
import com.example.bankomat.repository.XodimRepozitary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.example.bankomat.entity.enums.Huquqlar.*;

@Component
public class Yuklanish implements CommandLineRunner {
    @Autowired
    LavozimRepozitary lavozimRepozitary;
    @Autowired
    XodimRepozitary xodimRepozitary;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Value(value = "${spring.sql.init.mode}")
    String xolatBoshlanish;
    @Override
    public void run(String... args) throws Exception {
  if(xolatBoshlanish.equals("always")){
      final Huquqlar[] huquqlars = Huquqlar.values();
      final Lavozim admin = lavozimRepozitary.save(new Lavozim("Direktor", Arrays.asList(huquqlars)));
      final Lavozim user = lavozimRepozitary.save(new Lavozim("Xodim", Arrays.asList(ADDPOST, EDITPOST, READPOST, DELETEPOST, DELETEMYPOST)));
      xodimRepozitary.save(new Xodim("Jaloliddin","xushvaqtov@gmail.com",passwordEncoder.encode("AB8234652"),admin,true));
      xodimRepozitary.save(new Xodim("Asadbek","asad@gmail.com", passwordEncoder.encode("AB8234654"), user,true));
  }
    }
}

package com.example.bankomat.service;

import com.example.bankomat.entity.Lavozim;
import com.example.bankomat.entity.Manzil;
import com.example.bankomat.entity.Xodim;
import com.example.bankomat.payload.ApiRespons;
import com.example.bankomat.payload.AuthDTO;
import com.example.bankomat.payload.XodimDto;
import com.example.bankomat.repository.LavozimRepozitary;
import com.example.bankomat.repository.ManzilRepository;
import com.example.bankomat.repository.XodimRepozitary;
import com.example.bankomat.token.Token_Olish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class XodimService implements UserDetailsService {
    @Lazy
    @Autowired
    XodimRepozitary xodimRepozitary;
    @Autowired
    LavozimRepozitary lavozimRepozitary;
    @Autowired
    ManzilRepository manzilRepository;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    Token_Olish token_olish;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<Xodim> byUsername = xodimRepozitary.findByUsername(username);
        if (byUsername.isPresent()){
            return byUsername.get();
        }
        throw  new UsernameNotFoundException("Bunday foydalanuvchi mavjud emas");

    }
    public boolean XabarYuborish(String username, String emailkod){
        try {
            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            simpleMailMessage.setTo(username);
            simpleMailMessage.setFrom("xushvaqitovjaloliddin@gmail.com");
            simpleMailMessage.setSubject("Tasdiqlash kodi: ");
            simpleMailMessage.setText("<a href='http://localhost:8080/users/tasdiqlash?email="+username+"&emailkod="+emailkod+"'>Emailni tasdiqlash</a>");
            javaMailSender.send(simpleMailMessage);
            return true;
        }catch (Exception e){
            e.getStackTrace();
            return false;
        }
    }
    public ApiRespons xodimadd(XodimDto xodimDto, String username) {
        final Optional<Xodim> byUsername = xodimRepozitary.findByUsername(username);
        if(!byUsername.isPresent()){
        Manzil manzil=new Manzil();
        manzil.setViloyat(xodimDto.getManzil().getViloyat());
        manzil.setTuman(xodimDto.getManzil().getTuman());
        manzil.setKucha(xodimDto.getManzil().getKucha());
        manzilRepository.save(manzil);

        Lavozim lavozim=new Lavozim();
        lavozim.setLavozimNomi(xodimDto.getLavozim().getLavozimNomi());
        lavozim.setHuquqlarList(xodimDto.getLavozim().getHuquqlarList());
        lavozimRepozitary.save(lavozim);

        Xodim xodim=new Xodim();
        xodim.setFish(xodimDto.getFish());
        xodim.setPassword(passwordEncoder.encode(xodimDto.getPassword()));
        xodim.setUsername(xodim.getUsername());
        String emailcode= UUID.randomUUID().toString().substring(0,6);
        xodim.setEmailkod(emailcode);
        xodim.setLavozim(lavozim);
        xodim.setManzil(manzil);
            if(XabarYuborish(xodimDto.getUsername(), emailcode )){
                xodimRepozitary.save(xodim);
                return new ApiRespons("Ro'yhatdan muvaffaqiyatli o'tdingiz! Eletkron pochtangizni faollashtirish kodi yuborildi",true);
            }

        return new ApiRespons("emailda xatolik mavjud",false);
        }
        return new  ApiRespons("bunday xodim bazada mavjud emas!!",false);
    }


    public ApiRespons tasdiqlash(String username, String emailcode) {
        final Optional<Xodim> byUsernameAndEmailkod = xodimRepozitary.findByUsernameAndEmailkod(username, emailcode);
        if(byUsernameAndEmailkod.isPresent()){
            final Xodim xodim = byUsernameAndEmailkod.get();
            xodim.setEnabled(true);
            xodim.setEmailkod(null);
            xodimRepozitary.save(xodim);
            return new ApiRespons("akkaunt faolllashtirildi",true);

        }
        return new ApiRespons("bunday akkaunt royxatda mavjud emas",false);

    }

    public ApiRespons login(AuthDTO authDTO) {
        final Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword()));
        if(authenticate.isAuthenticated()){
            final Optional<Xodim> byUsernameAndEmailkod = xodimRepozitary.findByUsernameAndEmailkod(authDTO.getUsername(), null);
            if(byUsernameAndEmailkod.isPresent()){
                final Xodim principal =(Xodim) authenticate.getPrincipal();
                return new ApiRespons("Profilga xush kelibsiz //"+token_olish.Token_Olish(principal.getUsername(),principal.getLavozim()),true);
            }
            return new ApiRespons("akkauntingiz royhatdan otmagan",false);
        }
        return new ApiRespons("login yoki parol hato iltimos qaytakiriting",false);
    }
}

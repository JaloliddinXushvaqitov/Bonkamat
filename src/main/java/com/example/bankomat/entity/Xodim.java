package com.example.bankomat.entity;

import com.example.bankomat.entity.enums.Huquqlar;
import com.example.bankomat.entity.template.Abstract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Xodim extends Abstract implements UserDetails {
    private String fish;
    private String username;
    private String password;
    private String emailkod;
    @OneToOne
    private Lavozim lavozim;
    @OneToOne
    private Manzil manzil;
    public boolean accountNonExpired=true;
    public boolean isAccountNonLocked=true;
    public boolean isCredentialsNonExpired=true;
    public boolean isEnabled=false;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<Huquqlar> huquqlarList = this.lavozim.getHuquqlarList();
        List<GrantedAuthority> grantedAuthorityList= new ArrayList<>();
        for (Huquqlar huquqlar : huquqlarList) {
            grantedAuthorityList.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return huquqlar.name();
                }
            });
        }
        return grantedAuthorityList;
    }

    public Xodim(String fish, String username, String password, Lavozim lavozim, boolean isEnabled) {
        this.fish = fish;
        this.username = username;
        this.password = password;
        this.lavozim = lavozim;
        this.isEnabled = isEnabled;
    }
}

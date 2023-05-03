package com.example.bankomat.entity.karta;

import com.example.bankomat.entity.Lavozim;
import com.example.bankomat.entity.Manzil;
import com.example.bankomat.entity.template.Abstract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Users extends Abstract {

    @Column(nullable = false)
    private String fish;
    @Email
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String telRaqam;
    @Column(nullable = false)
    private String passwordRaqam;

    @OneToOne
    private Manzil manzil;

}

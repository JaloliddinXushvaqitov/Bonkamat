package com.example.bankomat.entity.karta;

import com.example.bankomat.entity.Bank;
import com.example.bankomat.entity.KartaTuri;
import com.example.bankomat.entity.template.Abstract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class KartaMuddati extends Abstract {
    private Integer kartaMuddat;
    @OneToOne
    private KartaTuri kartaTuri;

    @ManyToOne
    private Bank bank;
}

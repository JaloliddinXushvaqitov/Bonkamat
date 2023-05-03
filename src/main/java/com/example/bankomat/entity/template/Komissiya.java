package com.example.bankomat.entity.template;

import com.example.bankomat.entity.Bank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Komissiya extends Abstract {
    @Column(nullable = false)
    private Integer foiz;
    @OneToOne
    private Bank bank;
}

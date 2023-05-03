package com.example.bankomat.entity;

import com.example.bankomat.entity.template.Abstract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class KartaTuri extends Abstract {
    @Column(nullable = false)
    private String nomi;
    @Column(nullable = false,unique = true,length = 6)
    private String boshlanishKodi;
}

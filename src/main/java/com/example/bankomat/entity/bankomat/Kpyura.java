package com.example.bankomat.entity.bankomat;

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
public class Kpyura extends Abstract {
    @Column(nullable = false)
    private Integer ming_200;
    @Column(nullable = false)
    private Integer ming_100;
    @Column(nullable = false)
    private Integer ming_50;
    @Column(nullable = false)
    private Integer ming_20;
    @Column(nullable = false)
    private Integer ming_10;
    @Column(nullable = false)
    private Integer ming_5;
}

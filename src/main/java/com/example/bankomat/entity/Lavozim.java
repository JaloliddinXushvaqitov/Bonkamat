package com.example.bankomat.entity;

import com.example.bankomat.entity.enums.Huquqlar;
import com.example.bankomat.entity.template.Abstract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Lavozim extends Abstract {
    private String lavozimNomi;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Huquqlar> huquqlarList;
}

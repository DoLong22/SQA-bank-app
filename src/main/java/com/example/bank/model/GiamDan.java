package com.example.bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class GiamDan {
    private int kyTraNo;
    private double GocConLai;
    private double Goc;
    private double Lai;
    private double Tong;
}

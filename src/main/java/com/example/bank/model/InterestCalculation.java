package com.example.bank.model;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class InterestCalculation  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double loan;
    private int interestType;
    private float interestRate;
    private  int month;
    private double interestPerMonth;
    private double principalPerMonth;
    private double totalPerMonth;
}

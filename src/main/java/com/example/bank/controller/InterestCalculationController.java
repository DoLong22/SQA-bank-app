package com.example.bank.controller;

import com.example.bank.model.GiamDan;
import com.example.bank.service.InterestCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com .example.bank.model.InterestCalculation;
@RestController
@CrossOrigin("*")
@RequestMapping("/calculation")
public class InterestCalculationController {
    @Autowired
    private InterestCalculationService interestCalculationService;
    @GetMapping("")
    public InterestCalculation calculation1(){
        InterestCalculation interestCalculation = interestCalculationService.getCalcu();

        return interestCalculation;
    }

    @PostMapping("/bandau")
    public InterestCalculation calculation(@RequestBody InterestCalculation interestCalculation){
        interestCalculation.setInterestPerMonth((interestCalculation.getLoan()*interestCalculation.getInterestRate()*0.01)/12);
        interestCalculation.setPrincipalPerMonth(interestCalculation.getLoan()/interestCalculation.getMonth());
        interestCalculation.setTotalPerMonth(interestCalculation.getInterestPerMonth()+interestCalculation.getPrincipalPerMonth());
        return interestCalculation;

    }

    @PostMapping("/giamdan")
    public ResponseEntity<?> calculationGiamdan(@RequestBody InterestCalculation interestCalculation){
            List<GiamDan> giamDans = new ArrayList<>();
            for (int i = 0;i <= interestCalculation.getMonth(); i++ ){
                GiamDan giamDan = new GiamDan();
                giamDan.setKyTraNo(i);
                giamDan.setGocConLai(interestCalculation.getLoan()-i*interestCalculation.getLoan()/interestCalculation.getMonth());
                giamDan.setGoc(interestCalculation.getLoan()/interestCalculation.getMonth());
                giamDan.setLai(giamDan.getGoc()*interestCalculation.getInterestRate()*0.01*((interestCalculation.getMonth()-i)*0.083));
                giamDan.setTong(giamDan.getGoc()+giamDan.getLai());
                giamDans.add(giamDan);
        }
            return   ResponseEntity.ok(giamDans);

    }
}


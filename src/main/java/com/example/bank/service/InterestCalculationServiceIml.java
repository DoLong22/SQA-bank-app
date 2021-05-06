package com.example.bank.service;

import com.example.bank.model.InterestCalculation;
import org.springframework.stereotype.Component;

@Component
public class InterestCalculationServiceIml implements InterestCalculationService {
    private static InterestCalculation interestCalculation = new InterestCalculation(10,10,1,10,10,10,10,10);

    @Override
    public InterestCalculation getCalcu(){
        return interestCalculation;
    }
}

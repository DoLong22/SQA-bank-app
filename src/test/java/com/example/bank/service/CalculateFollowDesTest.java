package com.example.bank.service;

import com.example.bank.IntegrationTest;
import com.example.bank.model.LoanInformation;
import com.example.bank.model.PayInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IntegrationTest
@Transactional
public class CalculateFollowDesTest {
    private LoanInformation loanInformation;
    private PayInformation payTest;
    private LocalDate DATE_OF_LOAN_DEFAULT = LocalDate.of(2021, 05, 20);
    private int INTEREST_TYPE_DEFAULT = 2;
    private float INTEREST_RATE_DEFAULT = (float)3.8;
    private int NUM_OF_MONTHS_DEFAULT = 12;

    @Autowired
    private LoanService loanService;

    @BeforeEach
    void setUp() {
        loanInformation = new LoanInformation();
        loanInformation.setDateOfLoan(DATE_OF_LOAN_DEFAULT);
        loanInformation.setInterestType(INTEREST_TYPE_DEFAULT);
        loanInformation.setNumOfMonths(NUM_OF_MONTHS_DEFAULT);
        loanInformation.setInterestRate(INTEREST_RATE_DEFAULT);

        payTest = new PayInformation();
    }
    @Test
    void tinhNoConLai() {
        loanInformation.setLoan(10000000);
        loanInformation.setLoanPerMonth(loanInformation.getLoan()/loanInformation.getNumOfMonths());
        payTest.setCountMonth(0);
        payTest.setPayInformationFollowDecreasing(loanInformation);
        assertEquals(10000000, payTest.getExistence());
    }

}

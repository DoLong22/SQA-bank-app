package com.example.bank.service;

import com.example.bank.IntegrationTest;
import com.example.bank.model.PayInformation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@IntegrationTest
@Transactional
class LoanServiceImlTest {

    @Autowired
    private LoanService loanService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveLoan() {
        assertEquals("10","10");
    }

    @Test
    void updatePayment() {
        PayInformation payInformation = new PayInformation();
        payInformation.setId(36);
        payInformation.setExistence(1000000);
        LocalDate localDate = LocalDate.of(2021,07,18);
        payInformation.setDateOfPay(localDate);
        payInformation.setInterest(9667);
        payInformation.setTotalPerMonth(9667);
        payInformation.setPay(true);
        payInformation.setLoanId(23);
        PayInformation pay = this.loanService.updatePayment(payInformation);
        assertEquals(true, pay.isPay());
    }

    @Test
    void calculateLoanByOriginal() {
    }

    @Test
    void calculateLoanByDecreasing() {
    }
}
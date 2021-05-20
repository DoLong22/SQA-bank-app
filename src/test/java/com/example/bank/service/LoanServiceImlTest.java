package com.example.bank.service;

import com.example.bank.model.PayInformation;
import com.example.bank.repository.PayRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"testContext.xml"})
//@Transactional
class LoanServiceImlTest {

    private final PayRepository payRepository;

    @Autowired
    public LoanServiceImlTest(PayRepository payRepository) {
        this.payRepository = payRepository;
    }

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
    @Transactional
    void updatePayment() {
        PayInformation payInformation = new PayInformation();
        payInformation.setId(26);
        payInformation.setExistence(1000000);
        LocalDate localDate = LocalDate.of(2021,07,18);
        payInformation.setDateOfPay(localDate);
        payInformation.setInterest(9667);
        payInformation.setTotalPerMonth(9667);
        PayInformation pay = this.payRepository.saveAndFlush(payInformation);
    }

    @Test
    void calculateLoanByOriginal() {
    }

    @Test
    void calculateLoanByDecreasing() {
    }
}
package com.example.NBPRESTService.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class InputValidationServiceTest {

    @Autowired
    InputValidationService inputValidationService;

    @Test
    public void shouldValidateAsCorrectDate(){
        //given
        String exchangeDate = "2022-09-28";
        //when
        boolean result = inputValidationService.validateExchangeDate(exchangeDate);
        //then
        assertTrue(result);
    }
    @Test
    public void shouldValidateDateWithTooGreatDayValue(){
        //given
        String exchangeDate = "2022-09-40";
        //when
        boolean result = inputValidationService.validateExchangeDate(exchangeDate);
        //then
        assertFalse(result);
    }
    @Test
    public void shouldValidateDateWithTooGreatMonthValue(){
        //given
        String exchangeDate = "2022-31-21";
        //when
        boolean result = inputValidationService.validateExchangeDate(exchangeDate);
        //then
        assertFalse(result);
    }
    @Test
    public void shouldValidateDateWithTooGreatFebruaryValue(){
        //given
        String exchangeDate = "2022-02-30";
        //when
        boolean result = inputValidationService.validateExchangeDate(exchangeDate);
        //then
        assertFalse(result);
    }
    @Test
    public void shouldValidateDateWithTooMuchYearCharacters(){
        //given
        String exchangeDate = "20222-02-30";
        //when
        boolean result = inputValidationService.validateExchangeDate(exchangeDate);
        //then
        assertFalse(result);
    }
    @Test
    public void shouldValidateNonDateString(){
        //given
        String exchangeDate = "aaaaa";
        //when
        boolean result = inputValidationService.validateExchangeDate(exchangeDate);
        //then
        assertFalse(result);
    }


    @Test
    public void shouldValidateCorrectCurrencyCode(){
        //given
        String currencyCode = "USD";
        //when
        boolean result = inputValidationService.validateCurrencyCode(currencyCode);
        //then
        assertTrue(result);
    }
    @Test
    public void shouldValidateCurrencyCodeWithTooManyCharacters(){
        //given
        String currencyCode = "USDabc";
        //when
        boolean result = inputValidationService.validateCurrencyCode(currencyCode);
        //then
        assertFalse(result);
    }
    @Test
    public void shouldValidateCurrencyCodeWithTooLittleCharacters(){
        //given
        String currencyCode = "US";
        //when
        boolean result = inputValidationService.validateCurrencyCode(currencyCode);
        //then
        assertFalse(result);
    }
    @Test
    public void shouldValidateCurrencyCodeWithLowerCaseCharacters(){
        //given
        String currencyCode = "usd";
        //when
        boolean result = inputValidationService.validateCurrencyCode(currencyCode);
        //then
        assertFalse(result);
    }
    @Test
    public void shouldValidateCurrencyCodeWithSomeLowerCaseCharacters(){
        //given
        String currencyCode = "UsD";
        //when
        boolean result = inputValidationService.validateCurrencyCode(currencyCode);
        //then
        assertFalse(result);
    }

    @Test
    public void shouldValidateCorrectAmountValue(){
        //given
        double currencyAmount = 1;
        //when
        boolean result = inputValidationService.validateCurrencyAmount(currencyAmount);
        //then
        assertTrue(result);
    }
    @Test
    public void shouldValidateCorrectNonSingleAmountValue(){
        //given
        double currencyAmount = 13;
        //when
        boolean result = inputValidationService.validateCurrencyAmount(currencyAmount);
        //then
        assertTrue(result);
    }
    @Test
    public void shouldValidateCorrectNonCompleteAmountValue(){
        //given
        double currencyAmount = 1.03;
        //when
        boolean result = inputValidationService.validateCurrencyAmount(currencyAmount);
        //then
        assertTrue(result);
    }
    @Test
    public void shouldValidateNegativeAmountValue(){
        //given
        double currencyAmount = -3;
        //when
        boolean result = inputValidationService.validateCurrencyAmount(currencyAmount);
        //then
        assertFalse(result);
    }

}
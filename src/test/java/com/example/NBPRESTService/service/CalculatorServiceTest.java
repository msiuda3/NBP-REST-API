package com.example.NBPRESTService.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class CalculatorServiceTest {

    @Autowired
    private CalculatorService calculatorService;

    @Test
    public void shouldConvertToPLN(){
        //given
        double sourceCurrencyRate = 4;
        double targetCurrencyRate = 1;
        double sourceCurrencyValue = 1;
        //when
        double result = calculatorService.getTargetCurrencyValue(sourceCurrencyRate, targetCurrencyRate,sourceCurrencyValue);
        double expected = 4;
        //then
        assertEquals(expected, result);
    }
    @Test
    public void shouldConvertNonSingleValueToPLN(){
        //given
        double sourceCurrencyRate = 4;
        double targetCurrencyRate = 1;
        double sourceCurrencyValue = 10;
        //when
        double result = calculatorService.getTargetCurrencyValue(sourceCurrencyRate, targetCurrencyRate,sourceCurrencyValue);
        double expected = 40;
        //then
        assertEquals(expected, result);
    }

    @Test
    public void shouldConvertFromPLN(){
        //given
        double sourceCurrencyRate = 1;
        double targetCurrencyRate = 4;
        double sourceCurrencyValue = 1;
        //when
        double result = calculatorService.getTargetCurrencyValue(sourceCurrencyRate, targetCurrencyRate,sourceCurrencyValue);
        double expected = 0.25;
        //then
        assertEquals(expected, result);
    }
    @Test
    public void shouldConvertNonSingleValueFromPLN(){
        //given
        double sourceCurrencyRate = 1;
        double targetCurrencyRate = 4;
        double sourceCurrencyValue = 10;
        //when
        double result = calculatorService.getTargetCurrencyValue(sourceCurrencyRate, targetCurrencyRate,sourceCurrencyValue);
        double expected = 2.5;
        //then
        assertEquals(expected, result);
    }

    @Test
    public void shouldConvertBeetwenTwoForeignValuesWhenSourceIsGreater(){
        //given
        double sourceCurrencyRate = 8;
        double targetCurrencyRate = 4;
        double sourceCurrencyValue = 1;
        //when
        double result = calculatorService.getTargetCurrencyValue(sourceCurrencyRate, targetCurrencyRate,sourceCurrencyValue);
        double expected = 2;
        //then
        assertEquals(expected, result);
    }
    @Test
    public void shouldConvertBeetwenTwoForeignValuesWhenTargetIsGreater(){
        //given
        double sourceCurrencyRate = 4;
        double targetCurrencyRate = 8;
        double sourceCurrencyValue = 1;
        //when
        double result = calculatorService.getTargetCurrencyValue(sourceCurrencyRate, targetCurrencyRate,sourceCurrencyValue);
        double expected = 0.5;
        //then
        assertEquals(expected, result);
    }

    @Test
    public void shouldRoundUpToTwoDecimalSpaces(){
        //given
        double sourceCurrencyRate = 1;
        double targetCurrencyRate = 3;
        double sourceCurrencyValue = 1;
        //when
        double result = calculatorService.getTargetCurrencyValue(sourceCurrencyRate, targetCurrencyRate,sourceCurrencyValue);
        double expected = 0.33;
        //then
        assertEquals(expected, result);
    }
}
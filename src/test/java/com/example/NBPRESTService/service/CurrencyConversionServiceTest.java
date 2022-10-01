package com.example.NBPRESTService.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class CurrencyConversionServiceTest {
    @MockBean
    ExchangeRateService exchangeRateServiceMock;
    @MockBean
    CalculatorService calculatorServiceMock;
    @Autowired
    CurrencyConversionService currencyConversionService;

    @Test
    public void shouldConvertValue(){
        //given
        String sourceCurrencyCode = "USD";
        String targetCurrencyCode = "PLN";
        double sourceCurrencyValue = 2;
        String exchangeDate = "2022-09-29";

        double expected = 10;
        double sourceExchangeRate = 5;
        double targetExchangeRate = 1;
        //when
        when(exchangeRateServiceMock.getExchangeRateForCode(sourceCurrencyCode, exchangeDate)).thenReturn(sourceExchangeRate);
        when(exchangeRateServiceMock.getExchangeRateForCode(targetCurrencyCode, exchangeDate)).thenReturn(targetExchangeRate);

        when(calculatorServiceMock.getTargetCurrencyValue(sourceExchangeRate, targetExchangeRate, sourceCurrencyValue)).thenReturn(expected);

        double result = currencyConversionService.convert(sourceCurrencyCode, targetCurrencyCode, sourceCurrencyValue, exchangeDate).getTargetCurrencyValue();
        //then
        assertEquals(expected, result);
    }
}
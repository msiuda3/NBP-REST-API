package com.example.NBPRESTService.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculatorService {
    public double getTargetCurrencyValue(double sourceCurrencyRate, double targetCurrencyRate, double sourceCurrencyValue){
        double result = (sourceCurrencyRate * sourceCurrencyValue) / targetCurrencyRate;
        return roundUp(result, 2);
    }
    private double roundUp(double value, int spaces){
        return new BigDecimal(value).setScale(spaces, RoundingMode.DOWN).doubleValue();
    }
}

package com.example.NBPRESTService.service;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class InputValidationService {
    public boolean validateCurrencyCode(String currencyCode){
        String pattern = "([A-Z]){3}";
        return currencyCode.matches(pattern);
    }
    public boolean validateCurrencyAmount(double currencyAmount){
        return currencyAmount >= 0;
    }
    public boolean validateExchangeDate(String date){
        String dateFormat = "yyyy-MM-dd";
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}

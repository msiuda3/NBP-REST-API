package com.example.NBPRESTService.controller;

import com.example.NBPRESTService.service.CurrencyConversionService;
import com.example.NBPRESTService.service.DateService;
import com.example.NBPRESTService.service.InputValidationService;
import com.example.NBPRESTService.exception.InvalidDataException;
import com.example.NBPRESTService.model.ConversionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConversionController {
    @Autowired
    private CurrencyConversionService currencyConversionService;
    @Autowired
    private InputValidationService inputValidationService;
    @Autowired
    private DateService dateService;

    private final String DEFAULT_SOURCE_CURRENCY = "PLN";
    private final String DEFAULT_TARGET_CURRENCY = "PLN";
    private final String DEFAULT_VALUE = "1";

    @Value("${messages.error.currencySource}")
    private String BAD_SOURCE_CURRENCY;
    @Value("${messages.error.currencyTarget}")
    private String BAD_TARGET_CURRENCY;
    @Value("${messages.error.currencyValue}")
    private final String BAD_AMOUNT = "BAD AMOUNT";
    @Value("${messages.error.exchangeDate}")
    private final String BAD_EXCHANGE_DATE = "BAD DATE";

    @GetMapping("/convertion")
    public ConversionResponse convert(
            @RequestParam(defaultValue = DEFAULT_SOURCE_CURRENCY) String sourceCurrency,
            @RequestParam(defaultValue = DEFAULT_TARGET_CURRENCY) String targetCurrency,
            @RequestParam(required = false) String exchangeDate,
            @RequestParam(defaultValue = DEFAULT_VALUE) double sourceCurrencyValue
    ){
        if(exchangeDate == null){
            exchangeDate = dateService.getTodaysDate();
        }
        if(!inputValidationService.validateCurrencyCode(sourceCurrency)){
            throw new InvalidDataException(BAD_SOURCE_CURRENCY);
        }
        if(!inputValidationService.validateCurrencyCode(targetCurrency)){
            throw new InvalidDataException(BAD_TARGET_CURRENCY);
        }
        if(!inputValidationService.validateCurrencyAmount(sourceCurrencyValue)){
            throw new InvalidDataException(BAD_AMOUNT);
        }
        if(!inputValidationService.validateExchangeDate(exchangeDate)){
            throw new InvalidDataException(BAD_EXCHANGE_DATE);
        }
        return currencyConversionService.convert(sourceCurrency, targetCurrency, sourceCurrencyValue, exchangeDate);
    }
}

package com.example.NBPRESTService.Service;

import com.example.NBPRESTService.model.ConversionResponse;
import com.example.NBPRESTService.repository.CacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConversionService {
    @Autowired
    private ExchangeRateService exchangeRateService;
    @Autowired
    private CalculatorService calculatorService;
    public ConversionResponse convert(String sourceCurrencyCode, String targetCurrencyCode, double sourceCurrencyValue, String exchangeDate){
        ConversionResponse response = new ConversionResponse();
        response.setTargetCurrencyValue(getValueInTargetCurrency(sourceCurrencyValue,
                sourceCurrencyCode,
                targetCurrencyCode,
                exchangeDate
                )
        );
        response.setExchangeDate(exchangeDate);
        response.setTargetCurrencyCode(targetCurrencyCode);
        response.setSourceCurrencyValue(sourceCurrencyValue);
        response.setSourceCurrencyCode(sourceCurrencyCode);
        return response;
    }
    private double getValueInTargetCurrency(double sourceCurrencyValue, String sourceCurrencyCode, String targetCurrencyCode, String exchangeDate){
        double sourceCurrencyExchangeRate = exchangeRateService.getExchangeRateForCode(sourceCurrencyCode, exchangeDate);
        double targetCurrencyExchangeRate = exchangeRateService.getExchangeRateForCode(targetCurrencyCode, exchangeDate);
        return calculatorService.getTargetCurrencyValue(sourceCurrencyExchangeRate, targetCurrencyExchangeRate, sourceCurrencyValue);
    }
}

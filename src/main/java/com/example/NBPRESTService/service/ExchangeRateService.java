package com.example.NBPRESTService.service;

import com.example.NBPRESTService.model.ExchangeRate;
import com.example.NBPRESTService.repository.CacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateService {
    @Autowired
    private CacheRepository cacheRepository;
    @Autowired
    private NBPApiService nbpApiService;
    public double getExchangeRateForCode(String currencyCode, String exchangeDate){
        if(currencyCode.equals("PLN")){
            return 1;
        }
        if(cacheRepository.findExchangeRateForCurrencyCode(currencyCode, exchangeDate).size() == 0){
            double exchangeRate = nbpApiService.getExchangeRateFromApi(currencyCode, exchangeDate);
            ExchangeRate exchangeRateEntry = new ExchangeRate();
            exchangeRateEntry.setExchangeRate(exchangeRate);
            exchangeRateEntry.setCurrencyCode(currencyCode);
            exchangeRateEntry.setExchangeDate(exchangeDate);
            cacheRepository.save(exchangeRateEntry);
            return exchangeRate;
        }
        else{
            ExchangeRate exchangeRate = cacheRepository.findExchangeRateForCurrencyCode(currencyCode, exchangeDate).get(0);
            return exchangeRate.getExchangeRate();
        }
    }

}

package com.example.NBPRESTService.service;

import com.example.NBPRESTService.exception.ResultNotFoundException;
import com.example.NBPRESTService.model.NBPApiReponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NBPApiService {
    @Value("${api.nbp.url.base}")
    private String url;
    @Value("${api.nbp.url.endpoint}")
    private String endpoint;

    public double getExchangeRateFromApi(String currencyCode, String exchangeDate){
        NBPApiReponse apiReponse = callApi(currencyCode, exchangeDate);
        double exchangeRate = apiReponse.getRates()[0].getMid();
        return exchangeRate;
    }

    private NBPApiReponse callApi(String currencyCode, String exchangeDate){
        RestTemplate restTemplate = new RestTemplate();
        String completeUrl = url + endpoint.replace("{code}", currencyCode).replace("{date}", exchangeDate) + "?format=json";
        try {
            return restTemplate.getForObject(completeUrl, NBPApiReponse.class);
        }catch (Exception e){
            throw new ResultNotFoundException("");
        }
    }
}

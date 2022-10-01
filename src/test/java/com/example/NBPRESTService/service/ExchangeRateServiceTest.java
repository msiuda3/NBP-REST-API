package com.example.NBPRESTService.service;

import com.example.NBPRESTService.exception.ResultNotFoundException;
import com.example.NBPRESTService.model.ExchangeRate;
import com.example.NBPRESTService.repository.CacheRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class ExchangeRateServiceTest {

    @MockBean
    NBPApiService nbpApiServiceMock;
    @MockBean
    CacheRepository cacheRepositoryMock;

    @Autowired
    ExchangeRateService exchangeRateService;

    @Test
    public void shouldReturnOneForPLN(){
        //given
        String currencyCode = "PLN";
        String exchangeDate = "2022-09-29";
        //when
        double result = exchangeRateService.getExchangeRateForCode(currencyCode, exchangeDate);
        double expected = 1;
        //then
        assertEquals(expected, result);
    }
    @Test
    public void shouldReturnFromAPI(){
        //given
        String currencyCode = "USD";
        String exchangeDate = "2022-09-29";
        //when
        List<ExchangeRate> exchangeRates = new LinkedList<>();
        when(cacheRepositoryMock.findExchangeRateForCurrencyCode(currencyCode, exchangeDate)).thenReturn(exchangeRates);

        double expected = 3;
        when(nbpApiServiceMock.getExchangeRateFromApi(currencyCode, exchangeDate)).thenReturn(expected);

        double result = exchangeRateService.getExchangeRateForCode(currencyCode, exchangeDate);
        //then
        assertEquals(expected, result);
        verify(nbpApiServiceMock).getExchangeRateFromApi(currencyCode, exchangeDate);
    }

    @Test
    public void shouldNotCallAPI(){
        //given
        String currencyCode = "USD";
        String exchangeDate = "2022-09-29";
        //when
        double expected = 3;
        List<ExchangeRate> exchangeRates = new LinkedList<>();
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setCurrencyCode(currencyCode);
        exchangeRate.setExchangeDate(exchangeDate);
        exchangeRate.setExchangeRate(expected);
        exchangeRates.add(exchangeRate);
        when(cacheRepositoryMock.findExchangeRateForCurrencyCode(currencyCode, exchangeDate)).thenReturn(exchangeRates);

        when(nbpApiServiceMock.getExchangeRateFromApi(currencyCode, exchangeDate)).thenReturn(expected);

        double result = exchangeRateService.getExchangeRateForCode(currencyCode, exchangeDate);
        //then
        assertEquals(expected, result);
        verify(nbpApiServiceMock, never()).getExchangeRateFromApi(currencyCode, exchangeDate);
    }


    @Test
    public void shouldThrowNotFoundException(){
        //given
        String currencyCode = "USD";
        String exchangeDate = "2022-09-29";
        //when
        List<ExchangeRate> exchangeRates = new LinkedList<>();
        when(cacheRepositoryMock.findExchangeRateForCurrencyCode(currencyCode, exchangeDate)).thenReturn(exchangeRates);

        when(nbpApiServiceMock.getExchangeRateFromApi(currencyCode, exchangeDate)).thenThrow(ResultNotFoundException.class);

        assertThrows(ResultNotFoundException.class, () -> {
            exchangeRateService.getExchangeRateForCode(currencyCode, exchangeDate);
        });
    }



}
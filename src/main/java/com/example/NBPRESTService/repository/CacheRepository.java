package com.example.NBPRESTService.repository;

import com.example.NBPRESTService.model.ExchangeRate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CacheRepository extends CrudRepository<ExchangeRate, Long> {
    @Query("SELECT e FROM ExchangeRate e WHERE e.currencyCode = ?1 AND e.exchangeDate = ?2")
    List<ExchangeRate> findExchangeRateForCurrencyCode(String currencyCode, String exchangeDate);
}

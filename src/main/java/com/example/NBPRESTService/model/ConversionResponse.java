package com.example.NBPRESTService.model;

import java.util.Date;

public class ConversionResponse {

    private String sourceCurrencyCode;
    private double sourceCurrencyValue;
    private String targetCurrencyCode;
    private double targetCurrencyValue;
    private String exchangeDate;

    public String getSourceCurrencyCode() {
        return sourceCurrencyCode;
    }

    public void setSourceCurrencyCode(String sourceCurrencyCode) {
        this.sourceCurrencyCode = sourceCurrencyCode;
    }

    public double getSourceCurrencyValue() {
        return sourceCurrencyValue;
    }

    public void setSourceCurrencyValue(double sourceCurrencyValue) {
        this.sourceCurrencyValue = sourceCurrencyValue;
    }

    public String getTargetCurrencyCode() {
        return targetCurrencyCode;
    }
    public double getTargetCurrencyValue() {
        return targetCurrencyValue;
    }

    public void setTargetCurrencyValue(double targetCurrencyValue) {
        this.targetCurrencyValue = targetCurrencyValue;
    }

    public String getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(String exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public void setTargetCurrencyCode(String targetCurrencyCode) {
        this.targetCurrencyCode = targetCurrencyCode;
    }
}

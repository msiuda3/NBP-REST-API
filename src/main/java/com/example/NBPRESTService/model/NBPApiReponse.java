package com.example.NBPRESTService.model;

public class NBPApiReponse {
    private String table;
    private String currency;
    private String code;
    private RateEntry[] rates;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public RateEntry[] getRates() {
        return rates;
    }

    public void setRates(RateEntry[] rates) {
        this.rates = rates;
    }
}
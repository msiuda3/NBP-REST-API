# NBP-REST-API
 A RESTful API returning converted currency. API built using Spring Boot
# Usage
 API accepts GET request on endpoint "/convertion" that returns converted value for given currencies and value of the source currency.
 Endpoint accepts following parameters: <br/>
 sourceCurrency = 3-letter code of the initial currency user wants to convert the value from. Default value: "PLN" <br/>
 targetCurrency = 3-letter code of the target currency user wants to convert the value to. Default value: "PLN" <br/>
 sourceCurrencyValue = Non-negative value in the initial currency. Default value: 1 <br/>
 exchangeDate = Date from which to draw the exchange rates in yyyy-mm-dd format . Default value: current day <br/>
 In case of a missing paramter, the API will use the default value. In case of an invalid parameter the API will return 400 error.<br/>
 The response is given in a json format with folowing properties:
 sourceCurrencyCode: 3-letter code of the initial currency was converted from
 targetCurrencyCode: 3-letter code of the target currency was converted to
 sourceCurrencyValue: value in the initial currency
 
 # Example request
 Given request "http://localhost:8080/convertion?sourceCurrency=USD&sourceCurrencyValue=3&targetCurrency=USD&exchangeDate=2022-09-29". The API will respond with
 
 {"sourceCurrencyCode":"USD","sourceCurrencyValue":3.0,"targetCurrencyCode":"USD","targetCurrencyValue":3.0,"exchangeDate":"2022-09-29"}

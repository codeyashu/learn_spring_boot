package com.code.rsi.producingwebservice;

import io.rsi.codespace.CountryRequest;
import io.rsi.codespace.CountryResponse;
import io.rsi.codespace.CurrencyRequest;
import io.rsi.codespace.CurrencyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {

    private static final String NAMESPACE_URI = "http://codespace.rsi.io";

    private final CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CountryRequest")
    @ResponsePayload
    public CountryResponse getCountry(@RequestPayload CountryRequest countryRequest) {
        CountryResponse countryResponse = new CountryResponse();
        countryResponse.setCountry(countryRepository.findCountry(countryRequest.getName()));

        return countryResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CurrencyRequest")
    @ResponsePayload
    public CurrencyResponse getCurrency(@RequestPayload CurrencyRequest request) {
        CurrencyResponse response = new CurrencyResponse();
        response.setCurrency(countryRepository.findCurrency(request.getCountryName()));

        return response;
    }
}

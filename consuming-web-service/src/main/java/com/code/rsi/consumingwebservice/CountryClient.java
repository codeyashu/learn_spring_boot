package com.code.rsi.consumingwebservice;

import com.example.consumingwebservice.wsdl.CountryRequest;
import com.example.consumingwebservice.wsdl.CountryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

@Slf4j
public class CountryClient extends WebServiceGatewaySupport {
    public CountryResponse getCountry(String country) {
        CountryRequest request = new CountryRequest();
        request.setName(country);

        logger.info("Requesting location for " + country);

        CountryResponse response = (CountryResponse) getWebServiceTemplate().marshalSendAndReceive("http://localhost:8080/ws/countries",
                request,
                new SoapActionCallback("http://codespace.rsi.io/CountryRequest"));
        return response;
    }

}

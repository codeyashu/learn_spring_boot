package com.code.rsi.consumingwebservice;

import com.example.consumingwebservice.wsdl.CountryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class ConsumingWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumingWebServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner lookup(CountryClient quoteClient) {
        return args -> {
            String country = "Spain";

            if (args.length > 0) {
                country = args[0];
            }

            CountryResponse response = quoteClient.getCountry(country);
            log.error(response.getCountry().getCurrency().value());
        };
    }

}

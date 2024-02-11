package com.yerlal.dtc.services;

import com.yerlal.dtc.apis.CurrencySource;
import com.yerlal.dtc.apis.manyapis.APIresponses.CurrencyConversionDTO;
import com.yerlal.dtc.apis.manyapis.APIresponses.ExchangeRateDTO;
import com.yerlal.dtc.apis.manyapis.CurrencyAPIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

@Service
@Slf4j
public class CurrencyService {

    private final CurrencySource source;

    @Autowired
    public CurrencyService(CurrencyAPIService source) {
        this.source = source;
    }


    public ExchangeRateDTO getExchangeRate(String from, String to) throws RestClientException{
        return source.getExchangeRate(from, to);
    }

    public CurrencyConversionDTO convert(String from, String to, Integer amount) throws RestClientException{
        return source.convertCurrency(from, to, amount);
    }
}

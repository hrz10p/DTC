package com.yerlal.dtc.apis.manyapis;

import com.yerlal.dtc.apis.CurrencySource;
import com.yerlal.dtc.apis.manyapis.APIresponses.CurrencyConversionDTO;
import com.yerlal.dtc.apis.manyapis.APIresponses.CurrencyListDTO;
import com.yerlal.dtc.apis.manyapis.APIresponses.ExchangeRateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Component
@Slf4j
public class CurrencyAPIService implements CurrencySource {

    @Value("${MANYAPIS_BASEURL}")
    private String baseUrl;

    @Value("${CURRENCY_APIKEY}")
    private String apiKey;

    private final RestTemplate restTemplate;

    @Autowired
    public CurrencyAPIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-api-key", apiKey);
        return headers;
    }

    public CurrencyListDTO listAvailableCurrencies() {
        String url = baseUrl + "/listcurrency";
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());
        ResponseEntity<CurrencyListDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, CurrencyListDTO.class);
        return response.getBody();
    }

    public ExchangeRateDTO getExchangeRate(String fromCurrency, String toCurrency) throws RestClientException {
        String url = String.format(baseUrl + "/v1-convert-currency?from=%s&to=%s&amount=%d", fromCurrency, toCurrency, 1);
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());
        ResponseEntity<ExchangeRateDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, ExchangeRateDTO.class);
        return response.getBody();
    }

    public CurrencyConversionDTO convertCurrency(String fromCurrency, String toCurrency, Integer amount) throws RestClientException {
        String url = String.format(baseUrl + "/v1-convert-currency?from=%s&to=%s&amount=%d", fromCurrency, toCurrency, amount);
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());
        ResponseEntity<CurrencyConversionDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, CurrencyConversionDTO.class);
        return response.getBody();
    }
}

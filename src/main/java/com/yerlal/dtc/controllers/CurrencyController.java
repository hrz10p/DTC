package com.yerlal.dtc.controllers;

import com.yerlal.dtc.apis.manyapis.APIresponses.CurrencyConversionDTO;
import com.yerlal.dtc.apis.manyapis.APIresponses.ExchangeRateDTO;
import com.yerlal.dtc.services.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/")
@Slf4j
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping(value = "/getRate/{currencyFrom}/{currencyTo}")
    public ResponseEntity<ExchangeRateDTO> getExchangeRate(
            @PathVariable("currencyFrom") String currencyFrom,
            @PathVariable("currencyTo") String currencyTo) {
        ExchangeRateDTO exchangeRate = currencyService.getExchangeRate(currencyFrom, currencyTo);
        return ResponseEntity.ok(exchangeRate);
    }


    @GetMapping("/convert")
    public ResponseEntity<CurrencyConversionDTO> convertCurrency(
            @RequestParam String currencyFrom,
            @RequestParam String currencyTo,
            @RequestParam Integer amount) {
        CurrencyConversionDTO conversionResult = currencyService.convert(currencyFrom, currencyTo, amount);
        return ResponseEntity.ok(conversionResult);
    }
}

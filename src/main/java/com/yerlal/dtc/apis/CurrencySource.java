package com.yerlal.dtc.apis;

import com.yerlal.dtc.apis.manyapis.APIresponses.CurrencyConversionDTO;
import com.yerlal.dtc.apis.manyapis.APIresponses.CurrencyListDTO;
import com.yerlal.dtc.apis.manyapis.APIresponses.ExchangeRateDTO;

public interface CurrencySource {
    public CurrencyListDTO listAvailableCurrencies();
    public ExchangeRateDTO getExchangeRate(String fromCurrency, String toCurrency);
    public CurrencyConversionDTO convertCurrency(String fromCurrency, String toCurrency, Integer amount);
}

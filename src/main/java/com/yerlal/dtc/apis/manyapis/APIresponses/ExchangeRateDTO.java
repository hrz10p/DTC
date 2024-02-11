package com.yerlal.dtc.apis.manyapis.APIresponses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ExchangeRateDTO {
    private String from;
    private String to;
    private Double exchangeRate;

}

package com.yerlal.dtc.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.util.Date;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleConflict(Exception ex) {
        String bodyOfResponse = "An error occurred: " + ex.getMessage();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // Default status

        if (ex instanceof RestClientException) {
            HttpClientErrorException httpEx = (HttpClientErrorException) ex;
            ErrorResponse resp = httpEx.getResponseBodyAs(ErrorResponse.class);
            bodyOfResponse = resp.getMessage();
            status = (HttpStatus) httpEx.getStatusCode(); // This should directly return HttpStatus
        }

        ErrorResponse errorResponse = new ErrorResponse(bodyOfResponse, new Date());

        log.error("{}" , ex.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }

    public static class ErrorResponse {
        private final String message;
        private final Date timestamp;

        public ErrorResponse(String message, Date timestamp) {
            this.message = message;
            this.timestamp = timestamp;
        }

        // Getters
        public String getMessage() {
            return message;
        }

        public Date getTimestamp() {
            return timestamp;
        }
    }
}

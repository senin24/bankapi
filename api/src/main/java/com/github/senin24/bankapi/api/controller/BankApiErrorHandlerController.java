package com.github.senin24.bankapi.api.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class BankApiErrorHandlerController {

    private final MediaType errorMediaType = MediaType.parseMediaType("application/vnd.error");
    VndErrors vndErrors;

//    ResponseEntity<VndErrors> notFoundException (Cu)

}

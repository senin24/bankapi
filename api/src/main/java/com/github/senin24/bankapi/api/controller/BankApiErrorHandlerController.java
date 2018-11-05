package com.github.senin24.bankapi.api.controller;

import com.github.senin24.bankapi.api.exception.AccountNotFoundException;
import com.github.senin24.bankapi.api.exception.CustomerNotFoundException;
import com.github.senin24.bankapi.api.exception.TransactNotFoundException;
import com.github.senin24.bankapi.api.exception.TransactRefusedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class BankApiErrorHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
        return getErrorDetailsResponseEntity(request, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleCustomerNotFoundException(CustomerNotFoundException ex, WebRequest request) {
        return getErrorDetailsResponseEntity(request, ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleAccounrNotFoundException(AccountNotFoundException ex, WebRequest request) {
        return getErrorDetailsResponseEntity(request, ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleTransactNotFoundException(TransactNotFoundException ex, WebRequest request) {
        return getErrorDetailsResponseEntity(request, ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactRefusedException.class)
    public final ResponseEntity<ErrorDetails> handleTransactRefusedException(TransactRefusedException ex, WebRequest request) {
        return getErrorDetailsResponseEntity(request, ex.getMessage(), HttpStatus.NOT_IMPLEMENTED);
    }

    private ResponseEntity<ErrorDetails> getErrorDetailsResponseEntity(WebRequest request, String message, HttpStatus notFound) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), message,
                request.getDescription(true));
        return new ResponseEntity<>(errorDetails, notFound);
    }


}

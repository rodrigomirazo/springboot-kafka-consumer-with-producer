package com.springboot.kafka_trainning.controller;

import com.springboot.kafka_trainning.execptions.AccountNotFound;
import com.springboot.kafka_trainning.execptions.BalanceExceeded;
import com.springboot.kafka_trainning.execptions.InvalidRequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BankAccountExceptionHandler {

    @ExceptionHandler(AccountNotFound.class)
    public ResponseEntity<String> handleResourceNotFound(AccountNotFound e) {
        return new ResponseEntity<>("Account not found for: " + e.getOwnerName(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BalanceExceeded.class)
    public ResponseEntity<String> handleBalanceExceeded(BalanceExceeded e) {
        return new ResponseEntity<>("Exceeded Balance: " + e.getBalance(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRequestBody.class)
    public ResponseEntity<String> handleInvalidRequestBody(InvalidRequestBody e) {
        return new ResponseEntity<>("Invalid Request " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

package com.shopping.rewardPoints.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> myExpHandlerMain(Exception ie, WebRequest wr)  {
        ErrorDetails err = new ErrorDetails(LocalDateTime.now(), ie.getMessage(), wr.getDescription(false));
        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }
}

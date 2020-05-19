package com.assignment.transaction.exception;


import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
public class TransactionExceptionHandler {

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity handleMissingRequestParameterException(MissingServletRequestParameterException ex) {
        log.error("Exception occurred mandatory param missing{}", (Throwable) ex);
        return ResponseEntity.badRequest()
                .body("bad request");
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleGenericException(Exception ex) {
        log.error("Exception occurred  {}", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Unknown error occurred");
    }
}

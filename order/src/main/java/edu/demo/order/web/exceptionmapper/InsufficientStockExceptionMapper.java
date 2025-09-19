package edu.demo.order.web.exceptionmapper;

import edu.demo.order.service.InsufficientStockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InsufficientStockExceptionMapper {
    @ExceptionHandler(value = InsufficientStockException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ErrorResponse handleInvalidCredentialsException(InsufficientStockException ex) {
        return ErrorResponse.builder(ex, HttpStatusCode.valueOf(HttpStatus.PRECONDITION_FAILED.value()), ex.getMessage()).build();
    }
}


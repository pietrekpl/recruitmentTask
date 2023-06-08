package com.ludynia.recruitmentTask.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ErrorResponse handleForbiddenException(HttpClientErrorException.Forbidden exception) {
       return new ErrorResponse(HttpStatus.FORBIDDEN.toString(), exception.getMessage());
    }
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ErrorResponse handleForbiddenException(HttpClientErrorException.Unauthorized exception) {
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.toString(), exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGeneralException(Exception exception) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), exception.getMessage());
    }




}

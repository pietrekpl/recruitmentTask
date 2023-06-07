package com.ludynia.recruitmentTask.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {



    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGeneralException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), exception.getMessage());
    }

}

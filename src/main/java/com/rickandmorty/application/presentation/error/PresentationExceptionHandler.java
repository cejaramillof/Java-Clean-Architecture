package com.rickandmorty.application.presentation.error;

import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PresentationExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  void handleValidationError(Exception ex, HttpServletResponse response) throws Exception {
    response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getCause().getLocalizedMessage());
  }
}

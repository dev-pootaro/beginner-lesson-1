package com.example.lesson1.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * controller advice.
 *
 * @author pootaro.suzuki
 */
@RestControllerAdvice
public class LessonControllerAdvice {
  /**
   * exception handler.
   *
   * @param {Exception} e
   * @throws LessonException
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public String handleException(final Exception e) throws LessonException {
    throw new LessonException(e.getMessage());
  }
}

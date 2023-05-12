package com.example.lesson1.common;

/**
 * lesson origin exception.
 *
 * @author pootaro.suzuki
 */
public class LessonException extends Exception {
  private static final long serialVersionUID = 1L;

  LessonException(String msg) {
    super(msg);
  }
}

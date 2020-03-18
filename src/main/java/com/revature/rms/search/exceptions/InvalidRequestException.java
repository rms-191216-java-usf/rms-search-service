package com.revature.rms.search.exceptions;

public class InvalidRequestException extends RuntimeException {
  public InvalidRequestException() {
    super("Request was invalid");
  }

  public InvalidRequestException(String message) {
    super(message);
  }

  public InvalidRequestException(String message, Throwable cause) {
    super(message, cause);
  }
}

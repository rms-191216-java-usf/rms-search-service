package com.revature.rms.search.exceptions;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException() {
    super("Request could not be fulfilled!");
  }

  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}

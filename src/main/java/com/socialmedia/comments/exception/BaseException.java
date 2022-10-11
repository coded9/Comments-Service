package com.socialmedia.comments.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseException extends RuntimeException {

  private final String errorCode;

  public BaseException(String message,String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

}

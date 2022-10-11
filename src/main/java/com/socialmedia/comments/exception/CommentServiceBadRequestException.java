package com.socialmedia.comments.exception;

public  class CommentServiceBadRequestException extends BaseException {

  public CommentServiceBadRequestException(String message,String errorCode) {
    super(message,errorCode);
  }
}

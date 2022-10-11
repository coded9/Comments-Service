package com.socialmedia.comments.enums;

public enum CommentInteractionType {
  LIKE("LIKE"),
  DISLIKE("DISLIKE");

  private final String value;
  CommentInteractionType(String value){
    this.value = value;
  }
}

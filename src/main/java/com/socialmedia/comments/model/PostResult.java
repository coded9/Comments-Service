package com.socialmedia.comments.model;

import java.time.LocalDateTime;

public interface PostResult {
  Long getPostId();
  String getContent();
  Integer getLikes();
  Integer getDislikes();
  Integer getComments();
  LocalDateTime getUpdatedAt();
}

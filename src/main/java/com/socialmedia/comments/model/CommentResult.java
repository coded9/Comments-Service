package com.socialmedia.comments.model;

import java.time.LocalDateTime;


public interface CommentResult {
   Long getCommentId();
   String getContent();
   Integer getLikes();
   Integer getDislikes();
   Integer getReplies();
   LocalDateTime getUpdatedAt();
}

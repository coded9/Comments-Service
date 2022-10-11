package com.socialmedia.comments.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.socialmedia.comments.model.Post;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostsResponse {
     private List<PostDto> posts;
     private PaginationDto pagination;

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  @JsonInclude(Include.NON_NULL)
  public static class PostDto{
      private Long postId;
      private String content;
      private Integer likes;
      private Integer dislikes;
      private Integer comments;
      private LocalDateTime updatedAt;
  }
}

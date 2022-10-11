package com.socialmedia.comments.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentInteractionSummaryDto {
  private Integer likes;
  private Integer dislikes;
  private Integer replies;
}

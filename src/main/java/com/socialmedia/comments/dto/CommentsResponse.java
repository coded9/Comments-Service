package com.socialmedia.comments.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentsResponse {

    private List<CommentDto> comments;
    private PaginationDto pagination;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CommentDto{
         private Long id;
         private String content;
         private Integer likes;
         private Integer dislikes;
         private Integer replies;
         private LocalDateTime updatedAt;
    }
}

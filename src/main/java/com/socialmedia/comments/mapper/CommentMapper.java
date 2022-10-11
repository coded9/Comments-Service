package com.socialmedia.comments.mapper;

import com.socialmedia.comments.dto.CommentInteractionSummaryDto;
import com.socialmedia.comments.dto.CommentsResponse;
import com.socialmedia.comments.dto.CommentsResponse.CommentDto;
import com.socialmedia.comments.dto.PostInteractionSummaryDto;
import com.socialmedia.comments.model.Comment;
import com.socialmedia.comments.model.CommentInteractionSummaryResult;
import com.socialmedia.comments.model.CommentResult;
import com.socialmedia.comments.model.PostInteractionSummaryResult;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentMapper {

  private final PaginationMapper<CommentResult> paginationMapper;

  public CommentsResponse getCommentsResponse(Page<CommentResult> commentResultInterfacePage){
     return CommentsResponse.builder().comments(getComments(commentResultInterfacePage)).pagination(paginationMapper.getPaginationDto(commentResultInterfacePage))
         .build();
  }
  private List<CommentDto> getComments(Page<CommentResult> commentResultInterfacePage){
    return commentResultInterfacePage.stream().map(commentResult -> getCommentDto(commentResult)).collect(
        Collectors.toList());
  }
  private CommentDto getCommentDto(CommentResult commentResult){
    return CommentDto.builder().id(commentResult.getCommentId()).content(
                         commentResult.getContent()).likes(commentResult.getLikes()).dislikes(commentResult.getDislikes())
                     .replies(commentResult.getReplies()).updatedAt(commentResult.getUpdatedAt()).build();
  }

  public CommentDto getCommentDto(Comment comment){
    return CommentDto.builder().id(comment.getId()).content(
                         comment.getContent()).
                     updatedAt(comment.getUpdatedAt()).build();
  }

  public CommentInteractionSummaryDto getCommentInteractionSummaryDto(
      CommentInteractionSummaryResult commentInteractionSummaryResult){
    return CommentInteractionSummaryDto.builder().likes(commentInteractionSummaryResult.getLikesCount())
                                    .dislikes(commentInteractionSummaryResult.getDislikesCount()).replies(
            commentInteractionSummaryResult.getRepliesCount()).build();
  }
}

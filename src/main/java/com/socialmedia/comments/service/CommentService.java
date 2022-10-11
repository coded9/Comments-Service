package com.socialmedia.comments.service;

import com.socialmedia.comments.dto.CommentInteractionSummaryDto;
import com.socialmedia.comments.dto.CommentsResponse;
import com.socialmedia.comments.dto.CommentsResponse.CommentDto;
import com.socialmedia.comments.dto.PostInteractionSummaryDto;
import org.springframework.data.domain.Pageable;

public interface CommentService {

      CommentsResponse getCommentsResponse(Long userId,Long postId, Pageable pageable);

      CommentDto createComment(Long userId,Long postId, String content);

      CommentDto updateComment(Long commentId, String content);

      CommentInteractionSummaryDto likeComment(Long userId, Long commentId);

      CommentInteractionSummaryDto dislikeComment(Long userId, Long postId);

      CommentDto createReply(Long parentCommentId, Long userId, Long postId, String content);

}

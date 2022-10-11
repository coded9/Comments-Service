package com.socialmedia.comments.controller;

import static com.socialmedia.comments.util.EndPoints.COMMENTS;
import static com.socialmedia.comments.util.EndPoints.DISLIKE_COMMENT;
import static com.socialmedia.comments.util.EndPoints.LIKE_COMMENT;
import static com.socialmedia.comments.util.EndPoints.REPLIES;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.socialmedia.comments.dto.CommentInteractionSummaryDto;
import com.socialmedia.comments.dto.CommentsResponse;
import com.socialmedia.comments.dto.CommentsResponse.CommentDto;
import com.socialmedia.comments.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = COMMENTS, produces = APPLICATION_JSON_VALUE)
public class CommentController {

  private final CommentService commentService;

  @GetMapping
  public CommentsResponse getCommentsResponse(@RequestParam Long userId, @RequestParam Long postId, Pageable pageable){
    return commentService.getCommentsResponse(userId,postId,pageable);
  }


  @PostMapping(path = LIKE_COMMENT)
  public CommentInteractionSummaryDto likeComment(@RequestParam Long userId, @PathVariable Long commentId){
     return commentService.likeComment(userId,commentId);
  }

  @PostMapping(path = DISLIKE_COMMENT)
  public CommentInteractionSummaryDto dislikeComment(@RequestParam Long userId, @PathVariable Long commentId){
    return commentService.dislikeComment(userId,commentId);
  }

  @PostMapping(path = REPLIES)
  public CommentDto createReply(@PathVariable Long commentId, @RequestParam Long userId, @RequestParam Long postId, @RequestParam String content){
      return commentService.createReply(commentId,userId,postId,content);
  }




}

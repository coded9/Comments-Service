package com.socialmedia.comments.service.impl;

import com.socialmedia.comments.dto.CommentInteractionSummaryDto;
import com.socialmedia.comments.dto.CommentsResponse;
import com.socialmedia.comments.dto.CommentsResponse.CommentDto;
import com.socialmedia.comments.enums.PostReactionType;
import com.socialmedia.comments.mapper.CommentMapper;
import com.socialmedia.comments.model.*;
import com.socialmedia.comments.repository.CommentInteractionSummaryRepository;
import com.socialmedia.comments.repository.CommentReactionRepository;
import com.socialmedia.comments.repository.CommentRepository;
import com.socialmedia.comments.repository.ReplyRepository;
import com.socialmedia.comments.service.CommentService;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final CommentMapper commentMapper;
  private final CommentReactionRepository commentReactionRepository;
  private final CommentInteractionSummaryRepository commentInteractionSummaryRepository;
  private final ReplyRepository replyRepository;

  @Override
  public CommentsResponse getCommentsResponse(Long userId,Long postId, Pageable pageable) {
    Page<CommentResult> commentPage = commentRepository.getComments(userId, postId, pageable);
    return commentMapper.getCommentsResponse(commentPage);
  }

  @Transactional
  @Override
  public CommentDto createComment(Long userId, Long postId, String content) {
    Comment comment = commentRepository.save(getComment(userId,postId,content));
    return commentMapper.getCommentDto(comment);
  }

  @Transactional
  @Override
  public CommentDto updateComment(Long commentId, String content){
    Optional<Comment> commentOptional = commentRepository.findById(commentId);
    Comment comment = commentOptional.get();
    comment.setContent(content);
    comment.setUpdatedAt(LocalDateTime.now());
    return commentMapper.getCommentDto(comment);
  }


  @Override
  @Transactional
  public CommentInteractionSummaryDto likeComment(Long userId, Long commentId) {
    CommentReaction commentReaction = commentReactionRepository.findByAppUserIdAndCommentId(userId, commentId);
    if(commentReaction == null){
      commentReaction = getCommentReaction(userId,commentId);
    }
    commentReaction.setReactionType(PostReactionType.LIKE.getValue());
    commentReaction.setUpdatedAt(LocalDateTime.now());
    commentReactionRepository.save(commentReaction);
    return commentMapper.getCommentInteractionSummaryDto(updateCommentInteractionSummary(commentId));
  }


  @Override
  @Transactional
  public CommentInteractionSummaryDto dislikeComment(Long userId, Long commentId) {
    CommentReaction commentReaction = commentReactionRepository.findByAppUserIdAndCommentId(userId, commentId);
    if(commentReaction == null){
      commentReaction = getCommentReaction(userId,commentId);
    }
    commentReaction.setReactionType(PostReactionType.DISLIKE.getValue());
    commentReaction.setUpdatedAt(LocalDateTime.now());
    commentReactionRepository.save(commentReaction);
    return commentMapper.getCommentInteractionSummaryDto(updateCommentInteractionSummary(commentId));
  }

  @Override
  @Transactional
  public CommentDto createReply(Long parentCommentId, Long userId, Long postId, String content) {
    Comment comment = commentRepository.save(getComment(userId,postId,content));
    comment.setIsParent(false);
    replyRepository.save(getReply(parentCommentId,comment.getId()));
    return commentMapper.getCommentDto(comment);
  }



  private Reply getReply(Long parentCommentId, Long childCommentId){
    return Reply.builder().childComment(getComment(childCommentId)).parentComment(getComment(parentCommentId))
        .build();
  }

  private CommentReaction getCommentReaction(Long userId, Long commentId){
    return CommentReaction.builder().appUser(getAppUser(userId)).comment(getComment(commentId))
                       .createdAt(LocalDateTime.now()).build();
  }

  private AppUser getAppUser(Long userId){
    return AppUser.builder().id(userId).build();
  }
  private Post getPost(Long postId){
    return Post.builder().id(postId).build();
  }
  private Comment getComment(Long commentId){
    return Comment.builder().id(commentId).build();
  }


  private Comment getComment(Long userId, Long postId, String content){
    return Comment.builder().appUser(getAppUser(userId)).post(getPost(postId))
        .content(content).createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();
  }

  private CommentInteractionSummary getCommentInteractionSummary(Long commentId){
    return CommentInteractionSummary.builder().comment(getComment(commentId)).repliesCount(0).build();
  }

  private CommentInteractionSummaryResult updateCommentInteractionSummary(Long commentId){
    CommentInteractionSummaryResult commentInteractionSummaryResult = commentReactionRepository.getCommentReactionSummary(commentId);
    CommentInteractionSummary commentInteractionSummary = commentInteractionSummaryRepository.findByCommentId(commentId);
    if(commentInteractionSummary == null){
      commentInteractionSummary = getCommentInteractionSummary(commentId);
    }
    commentInteractionSummary.setLikesCount(commentInteractionSummaryResult.getLikesCount());
    commentInteractionSummary.setDislikesCount(commentInteractionSummaryResult.getDislikesCount());
    commentInteractionSummary.setUpdatedAt(LocalDateTime.now());
    commentInteractionSummaryRepository.save(commentInteractionSummary);
    return commentInteractionSummaryResult;
  }



}

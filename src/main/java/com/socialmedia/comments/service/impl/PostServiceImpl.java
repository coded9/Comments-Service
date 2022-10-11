package com.socialmedia.comments.service.impl;

import com.socialmedia.comments.dto.PostInteractionSummaryDto;
import com.socialmedia.comments.dto.PostsResponse;
import com.socialmedia.comments.enums.PostReactionType;
import com.socialmedia.comments.mapper.PostMapper;
import com.socialmedia.comments.model.*;
import com.socialmedia.comments.repository.PostInteractionSummaryRepository;
import com.socialmedia.comments.repository.PostReactionRepository;
import com.socialmedia.comments.repository.PostRepository;
import com.socialmedia.comments.service.PostService;
import java.time.LocalDateTime;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;
  private final PostMapper postMapper;
  private final PostReactionRepository postReactionRepository;
  private final PostInteractionSummaryRepository postInteractionSummaryRepository;

  @Override
  public PostsResponse getPosts(long userId, Pageable pageable) {
    Page<PostResult> postResultPage = postRepository.getPosts(userId,pageable);
    return postMapper.getPostsResponse(postResultPage);
  }

  @Override
  @Transactional
  public PostInteractionSummaryDto likePost(long userId, long postId) {
    PostReaction postReaction = postReactionRepository.findByAppUserIdAndPostId(userId, postId);
    if(postReaction == null){
        postReaction = getPostReaction(userId,postId);
    }
    postReaction.setReactionType(PostReactionType.LIKE.getValue());
    postReaction.setUpdatedAt(LocalDateTime.now());
    postReactionRepository.save(postReaction);
    return postMapper.getPostInteractionSummaryDto(updatePostInteractionSummary(postId));
  }


  @Override
  @Transactional
  public PostInteractionSummaryDto dislikePost(long userId, long postId) {
    PostReaction postReaction = postReactionRepository.findByAppUserIdAndPostId(userId, postId);
    if(postReaction == null){
      postReaction = getPostReaction(userId,postId);
    }
    postReaction.setReactionType(PostReactionType.DISLIKE.getValue());
    postReaction.setUpdatedAt(LocalDateTime.now());
    postReactionRepository.save(postReaction);
    return postMapper.getPostInteractionSummaryDto(updatePostInteractionSummary(postId));
  }


  private PostInteractionSummaryResult updatePostInteractionSummary(Long postId){
    PostInteractionSummaryResult postInteractionSummaryResult = postReactionRepository.getPostReactionSummary(postId);
    PostInteractionSummary postInteractionSummary = postInteractionSummaryRepository.findByPostId(postId);
    if(postInteractionSummary == null){
      postInteractionSummary = getPostInteractionSummary(postId);
    }
    postInteractionSummary.setLikesCount(postInteractionSummaryResult.getLikesCount());
    postInteractionSummary.setDislikesCount(postInteractionSummaryResult.getDislikesCount());
    postInteractionSummary.setUpdatedAt(LocalDateTime.now());
    postInteractionSummaryRepository.save(postInteractionSummary);
    return postInteractionSummaryResult;
  }

  private PostReaction getPostReaction(long userId, long postId){
    return PostReaction.builder().appUser(getAppUser(userId)).post(getPost(postId))
                       .createdAt(LocalDateTime.now()).build();
  }
  private AppUser getAppUser(Long userId){
    return AppUser.builder().id(userId).build();
  }
  private Post getPost(Long postId){
    return Post.builder().id(postId).build();
  }

  private PostInteractionSummary getPostInteractionSummary(Long postId){
      return PostInteractionSummary.builder().post(getPost(postId)).commentsCount(0).build();
  }

}

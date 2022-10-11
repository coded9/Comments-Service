package com.socialmedia.comments.service;

import com.socialmedia.comments.dto.PostInteractionSummaryDto;
import com.socialmedia.comments.dto.PostsResponse;
import org.springframework.data.domain.Pageable;

public interface PostService {
       PostsResponse getPosts(long userId, Pageable pageable);

       PostInteractionSummaryDto likePost(long userId, long postId);

       PostInteractionSummaryDto dislikePost(long userId, long postId);

}

package com.socialmedia.comments.controller;

import static com.socialmedia.comments.util.EndPoints.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.socialmedia.comments.dto.CommentsResponse;
import com.socialmedia.comments.dto.CommentsResponse.CommentDto;
import com.socialmedia.comments.dto.PostInteractionSummaryDto;
import com.socialmedia.comments.dto.PostsResponse;
import com.socialmedia.comments.service.CommentService;
import com.socialmedia.comments.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = POSTS, produces = APPLICATION_JSON_VALUE)
public class PostController {
    private final PostService postService;
    private final CommentService commentService;

    @GetMapping
    public PostsResponse getPosts(@RequestParam Long userId, Pageable pageable){
      return postService.getPosts(userId,pageable);
    }

    @GetMapping(path = POSTS_COMMENTS)
    public CommentsResponse getCommentsResponse(@RequestParam Long userId, @PathVariable Long postId, Pageable pageable){
        return commentService.getCommentsResponse(userId,postId,pageable);
    }

    @PostMapping(path = POSTS_COMMENTS)
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createComment(@RequestParam Long userId,@PathVariable Long postId,@RequestParam String content){
        return commentService.createComment(userId,postId,content);
    }

    @PatchMapping(path = POSTS_COMMENTS_COMMENT)
    public CommentDto updateComment(@PathVariable Long commentId, @RequestParam String content){
        return commentService.updateComment(commentId,content);
    }

    @PostMapping(path = LIKE_POST)
    public PostInteractionSummaryDto likePost(@RequestParam Long userId, @PathVariable Long postId){
       return postService.likePost(userId,postId);
    }

    @PostMapping(path = DISLIKE_POST)
    public PostInteractionSummaryDto dislikePost(@RequestParam Long userId, @PathVariable Long postId){
        return postService.dislikePost(userId,postId);
    }

}

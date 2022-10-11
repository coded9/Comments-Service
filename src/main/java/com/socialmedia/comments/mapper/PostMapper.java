package com.socialmedia.comments.mapper;

import com.socialmedia.comments.dto.PostInteractionSummaryDto;
import com.socialmedia.comments.dto.PostsResponse;
import com.socialmedia.comments.dto.PostsResponse.PostDto;
import com.socialmedia.comments.model.Post;
import com.socialmedia.comments.model.PostInteractionSummaryResult;
import com.socialmedia.comments.model.PostResult;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostMapper {
    private final PaginationMapper<Post> postPaginationMapper;
  private final PaginationMapper<PostResult> postResultPaginationMapper;

    public PostsResponse getPostsResponseFromPostPage(Page<Post> postPage){
         return PostsResponse.builder().posts(getPostsFromPostPage(postPage)).pagination(
             postPaginationMapper.getPaginationDto(postPage)).build();
    }

  public PostsResponse getPostsResponse(Page<PostResult> postResultPage){
    return PostsResponse.builder().posts(getPosts(postResultPage)).pagination(postResultPaginationMapper.getPaginationDto(postResultPage)).build();
  }

    private List<PostDto> getPosts(Page<PostResult> postResultPage){
        return postResultPage.getContent().stream().map(postResult -> getPostDto(postResult)).collect(Collectors.toList());
    }
  private List<PostDto> getPostsFromPostPage(Page<Post> postPage){
    return postPage.getContent().stream().map(post -> getPostDto(post)).collect(Collectors.toList());
  }

    private PostDto getPostDto(PostResult postResult){
      return PostDto.builder().postId(postResult.getPostId()).content(postResult.getContent())
          .dislikes(postResult.getDislikes()).likes(postResult.getLikes()).comments(postResult.getComments())
          .build();
    }

  private PostDto getPostDto(Post post){
    return PostDto.builder().postId(post.getId()).content(post.getContent())
                  .build();
  }

    public PostInteractionSummaryDto getPostInteractionSummaryDto(PostInteractionSummaryResult postInteractionSummaryResult){
      return PostInteractionSummaryDto.builder().likes(postInteractionSummaryResult.getLikesCount())
          .dislikes(postInteractionSummaryResult.getDislikesCount()).comments(
              postInteractionSummaryResult.getRepliesCount()).build();
    }

}

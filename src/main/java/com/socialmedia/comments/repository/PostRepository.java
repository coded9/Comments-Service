package com.socialmedia.comments.repository;

import com.socialmedia.comments.model.CommentResult;
import com.socialmedia.comments.model.Post;
import com.socialmedia.comments.model.PostResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post,Long> {
   Page<Post> findByAppUserId(Long userId, Pageable pageable);

   @Query(value = "select pt.id as postId, content as content, ifnull(likes_count,0) likes, ifnull(dislikes_count,0) dislikes, ifnull(comments_count,0) as comments,pt.updated_at as updatedAt from post pt \n"
       + "left join post_interaction_summary pis\n"
       + "on pt.id =  pis.post_id where user_id = :userId", nativeQuery = true)
   Page<PostResult> getPosts(@Param("userId") Long userId, Pageable pageable);

}

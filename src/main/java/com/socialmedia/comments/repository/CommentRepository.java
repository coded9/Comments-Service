package com.socialmedia.comments.repository;

import com.socialmedia.comments.model.Comment;
import com.socialmedia.comments.model.CommentResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment,Long> {

    Page<Comment> findByPostId(Long postId, Pageable pageable);

    @Query(value = "select ct.id as commentId, content as content, ifnull(likes_count,0) likes, ifnull(dislikes_count,0) dislikes, ifnull(replies_count,0) as replies,ct.updated_at as updatedAt from comment ct \n"
        + "left join comment_interaction_summary cis\n"
        + "on ct.id =  cis.comment_id where user_id = :userId and post_id = :postId", nativeQuery = true)
    Page<CommentResult> getComments(@Param("userId") Long userId, @Param("postId") Long postId, Pageable pageable);

    Comment findByAppUserIdAndPostId(Long userId, Long postId);

}

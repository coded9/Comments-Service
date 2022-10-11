package com.socialmedia.comments.repository;

import com.socialmedia.comments.model.CommentInteractionSummaryResult;
import com.socialmedia.comments.model.CommentReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReactionRepository extends JpaRepository<CommentReaction,Long> {

  CommentReaction findByAppUserIdAndCommentId(Long userId, Long commentId);

  @Query(value = "select SUM(CASE when reaction_type = 'LIKE' then 1 else 0 END) as likesCount, SUM(CASE when reaction_type = 'DISLIKE' then 1 else 0 END) as dislikesCount from comment_reaction where comment_id = :commentId",nativeQuery = true)
  CommentInteractionSummaryResult getCommentReactionSummary(@Param("commentId") Long commentId);

}

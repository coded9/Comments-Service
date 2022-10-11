package com.socialmedia.comments.repository;


import com.socialmedia.comments.model.PostInteractionSummaryResult;
import com.socialmedia.comments.model.PostReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostReactionRepository extends JpaRepository<PostReaction,Long> {
    PostReaction findByAppUserIdAndPostId(Long userId, Long postId);

    @Query(value = "select SUM(CASE when reaction_type = 'LIKE' then 1 else 0 END) as likesCount, SUM(CASE when reaction_type = 'DISLIKE' then 1 else 0 END) as dislikesCount from post_reaction where post_id = :postId",nativeQuery = true)
    PostInteractionSummaryResult getPostReactionSummary(@Param("postId") Long postId);
}

package com.socialmedia.comments.repository;

import com.socialmedia.comments.model.CommentInteractionSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentInteractionSummaryRepository extends
    JpaRepository<CommentInteractionSummary,Long> {
    CommentInteractionSummary findByCommentId(Long commentId);
}

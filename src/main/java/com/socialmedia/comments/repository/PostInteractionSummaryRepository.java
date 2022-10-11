package com.socialmedia.comments.repository;

import com.socialmedia.comments.model.PostInteractionSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostInteractionSummaryRepository extends JpaRepository<PostInteractionSummary,Long> {

  PostInteractionSummary findByPostId(Long postId);

}

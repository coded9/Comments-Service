package com.socialmedia.comments.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "post_interaction_summary")
public class PostInteractionSummary {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "post_id", referencedColumnName = "id")
  private Post post;
  private Integer likesCount;
  private Integer dislikesCount;
  private Integer commentsCount;
  private LocalDateTime updatedAt;
}

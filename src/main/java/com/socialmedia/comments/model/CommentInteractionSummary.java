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
@Table(name = "comment_interaction_summary")
public class CommentInteractionSummary {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "comment_id", referencedColumnName = "id")
  private Comment comment;
  private Integer likesCount;
  private Integer dislikesCount;
  private Integer repliesCount;
  private LocalDateTime updatedAt;
}

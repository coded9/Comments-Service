package com.socialmedia.comments.model;

import com.socialmedia.comments.enums.PostReactionType;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "post_reaction")
public class PostReaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "post_id", referencedColumnName = "id")
  private Post post;
  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private AppUser appUser;
  private String reactionType;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}

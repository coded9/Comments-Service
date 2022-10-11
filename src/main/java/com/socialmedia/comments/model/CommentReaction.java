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
@Table(name = "comment_reaction")
public class CommentReaction {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @ManyToOne
   @JoinColumn(name = "comment_id", referencedColumnName = "id")
   private Comment comment;
   @ManyToOne
   @JoinColumn(name = "user_id", referencedColumnName = "id")
   private AppUser appUser;
   private String reactionType;
   private LocalDateTime createdAt;
   private LocalDateTime updatedAt;
}

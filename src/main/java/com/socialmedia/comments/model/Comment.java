package com.socialmedia.comments.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class Comment {

  public static final String COMMENT_RESULT_SET_MAPPING = "commentResultSetMapping";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String content;
  @ManyToOne
  @JoinColumn(name = "post_id", referencedColumnName = "id")
  private Post post;
  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private AppUser appUser;
  private Boolean isParent;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}

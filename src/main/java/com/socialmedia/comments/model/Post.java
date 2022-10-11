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
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private AppUser appUser;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

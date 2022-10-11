package com.socialmedia.comments.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "app_user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class AppUser {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String fullName;
     private String userName;
     private String profilePicUrl;
     private LocalDateTime createdAt;
     private LocalDateTime updatedAt;
}

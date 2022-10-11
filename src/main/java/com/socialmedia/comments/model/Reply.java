package com.socialmedia.comments.model;


import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "reply")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    @JoinColumn(name ="parent_comment_id", referencedColumnName = "id")
    private Comment parentComment;
    @OneToMany
    @JoinColumn(name = "child_comment_id", referencedColumnName = "id")
    private Comment childComment;

}



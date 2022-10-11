package com.socialmedia.comments.dto;

import java.util.List;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUsersResponse {

     private List<AppUserDto> users;
     private PaginationDto pagination;

     @Getter
     @Setter
     @Builder
     @AllArgsConstructor
     @NoArgsConstructor
     public static class AppUserDto{
          private Long id;
          private String fullName;
          private String userName;
     }
}

package com.socialmedia.comments.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponseDto {
     private String errorCode;
     private String message;
     private Integer status;
     private LocalDateTime timestamp;
     private String path;
}

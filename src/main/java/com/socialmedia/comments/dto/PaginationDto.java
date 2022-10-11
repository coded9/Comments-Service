package com.socialmedia.comments.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationDto {
  private Boolean hasPrevious;
  private Boolean hasNext;
  private Integer pageNumber;
  private Integer totalSize;
  private Integer totalPages;
  private Long totalCount;
}

package com.socialmedia.comments.mapper;

import com.socialmedia.comments.dto.PaginationDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PaginationMapper<T> {

  public PaginationDto getPaginationDto(Page<T> page) {
    return PaginationDto.builder()
                        .hasPrevious(page.hasPrevious())
                        .hasNext(page.hasNext())
                        .pageNumber(page.getNumber())
                        .totalPages(page.getTotalPages())
                        .totalCount(page.getTotalElements())
                        .totalSize(page.getNumberOfElements())
                        .build();
  }
}

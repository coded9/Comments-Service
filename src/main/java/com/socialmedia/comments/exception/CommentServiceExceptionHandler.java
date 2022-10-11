package com.socialmedia.comments.exception;


import com.socialmedia.comments.dto.ErrorResponseDto;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class CommentServiceExceptionHandler {

  @ExceptionHandler({CommentServiceBadRequestException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponseDto handle(CommentServiceBadRequestException e, HttpServletRequest request) {
    return getErrorResponseDto(e, request, HttpStatus.BAD_REQUEST);
  }


  private ErrorResponseDto getErrorResponseDto(BaseException e, HttpServletRequest request,
                                               HttpStatus httpStatus) {
    log.error(e.getMessage(), e);
    return ErrorResponseDto.builder()
                           .errorCode(e.getErrorCode())
                           .message(e.getMessage())
                           .timestamp(
                               LocalDateTime.now())
                           .status(httpStatus.value())
                           .path(request.getRequestURI())
                           .build();
  }

}
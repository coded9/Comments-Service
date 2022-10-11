package com.socialmedia.comments.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {

  @GetMapping("/")
  public String index() {
    log.info("Request came");
    return "Greetings from Spring Boot!";
  }

}

package com.socialmedia.comments.controller;


import static com.socialmedia.comments.util.EndPoints.USERS;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.socialmedia.comments.dto.AppUsersResponse;
import com.socialmedia.comments.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = USERS, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AppUserController {

       private final AppUserService appUserService;

       @GetMapping(produces = APPLICATION_JSON_VALUE)
       public AppUsersResponse getUsers(Pageable pageable){
            return appUserService.getAppUsersResponse(pageable);
       }
}

package com.socialmedia.comments.service;

import com.socialmedia.comments.dto.AppUsersResponse;
import org.springframework.data.domain.Pageable;

public interface AppUserService {

      AppUsersResponse getAppUsersResponse(Pageable pageable);
}
